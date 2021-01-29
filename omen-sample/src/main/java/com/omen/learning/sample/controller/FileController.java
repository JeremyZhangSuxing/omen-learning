package com.omen.learning.sample.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.github.houbb.csv.util.CsvHelper;
import com.omen.learning.common.entity.JackSonDemo;
import com.omen.learning.common.support.JackJsonProUtils;
import com.omen.learning.common.utils.CommonFileUtils;
import com.omen.learning.sample.service.file.FileService;
import com.omen.learning.sample.support.DemoExcelDTO;
import com.omen.learning.sample.support.DemoExcelUploadDTO;
import com.omen.learning.sample.support.UploadDataListener;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author zhang.suxing
 * @date 2020/6/27 10:26
 **/
@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class FileController {
    private static final String FOLDER = "/tem/local";
    private final FileService fileService;

    @GetMapping("/{name}")
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String fileName = StringUtils.appendIfMissing(name, ".xlsx", "");
        try ( //1593225958838
              InputStream inputStream = new FileInputStream(new File(FOLDER, fileName));
              OutputStream outputStream = response.getOutputStream()
        ) {
            response.setHeader("Content-Disposition", "attachment; filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            log.info("exception{}", e.getMessage());
        }
    }

    /**
     * excel 无内存溢出
     */
    @GetMapping("/excel/download")
    public CommonCodeResponse exportExcel(HttpServletResponse response) throws IOException {
        List<DemoExcelDTO> data = fileService.buildExcelData();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "搬入搬出记录";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcelFactory.write(response.getOutputStream(), DemoExcelDTO.class).sheet("").doWrite(data);
        return CommonCodeResponse.success();
    }

    /**
     * 流直接转化成file
     */
    @GetMapping("/stream2file")
    public void fileUpload(MultipartFile multipartFile) throws IOException {
        fileService.fileUpload(multipartFile.getInputStream());
    }

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link com.omen.learning.sample.support.DemoExcelUploadDTO}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>
     * 3. 直接读即可
     * <p>
     * 当服务通过docker部署到linux环境上时会出现excel为空 且字体nullException
     * 在dockerfile 文件中加上  #安装字体
     * RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
     * 其他的报错场景 如变更 @RestController--->@controller 已验证无需变动
     */
    @PostMapping("/excel/upload")
    @ResponseBody
    public CommonCodeResponse upload(MultipartFile file) throws IOException {
        EasyExcelFactory.read(file.getInputStream(), DemoExcelUploadDTO.class, new UploadDataListener()).sheet().doRead();
        return CommonCodeResponse.success();
    }

    @PostMapping("/json")
    public CommonDataResponse<JackSonDemo> testJackSon(@RequestBody JackSonDemo jackSonDemo) {
        log.info("test data {}", jackSonDemo);
        return CommonDataResponse.success(JackSonDemo.buildOneObject());
    }

    @GetMapping("/json/list")
    public CommonDataResponse<List<JackSonDemo>> testJackSon1() {
        String key = JackJsonProUtils.convertToJson(JackSonDemo.buildManyObject());
        log.info("test data {}", key);
        List<JackSonDemo> jackSonDemos = JackJsonProUtils.convertToList(key, JackSonDemo.class);
        return CommonDataResponse.success(jackSonDemos);
    }

    @GetMapping("/csv/download")
    public void exportCsv(HttpServletResponse response) throws IOException {
        log.info("export csv --- star--{}", LocalDateTime.now());
        String fileName = UUID.randomUUID().toString() + ".csv";
        String path = "/Users/suxingzhang/Desktop/develop/moveInOut/" + fileName;
        CsvHelper.write(fileService.buildCsvData(), path);
        //必须有本地变量
        String name = "测试文件";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + CommonFileUtils.standardFileName(name) + ".csv");
        response.setContentType("application/csv; charset=utf-8");
        File file = new File(path);
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } finally {
            FileUtils.deleteQuietly(file);
        }
    }

    @PostMapping("/pdf")
    public CommonDataResponse<String> uploadPdf(@RequestParam MultipartFile multipartFile) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            ByteArrayBody byteArrayBody = new ByteArrayBody(multipartFile.getBytes(), ContentType.MULTIPART_FORM_DATA, multipartFile.getOriginalFilename());
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            HttpPost httpPost = new HttpPost("url");
            HttpEntity entity = entityBuilder.addPart("multipartFile", byteArrayBody).build();
            httpPost.setEntity(entity);
            httpPost.addHeader("Authorization", "X-CAT ");
            HttpResponse response = httpClient.execute(httpPost);
            // 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (Exception fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CommonDataResponse.success(result);
    }
}
