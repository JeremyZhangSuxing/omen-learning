package com.omen.learning.sample.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.omen.learning.common.entity.FileInfo;
import com.omen.learning.sample.file.FileService;
import com.omen.learning.sample.support.DemoExcelDTO;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/6/27 10:26
 **/
@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class FileController {
    private static final String FOLDER = "F:\\MyTools\\java\\imooc-security\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web";
    private final FileService fileService;

    @PostMapping
    public FileInfo upload(MultipartFile multipartFile) throws IOException {
        log.info("====== FileController upload param {},originName {}", multipartFile.getName(), multipartFile.getOriginalFilename());
        File localFile = new File(FOLDER, System.currentTimeMillis() + ".xlsx");
        multipartFile.transferTo(localFile);
        //如果是上传到云服务器 则需要拿到文件的输入流进行操作  multipartFile.getInputStream()
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{name}")
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String fileName = StringUtils.appendIfMissing(name, ".xlsx", "");
        try ( //1593225958838
              InputStream inputStream = new FileInputStream(new File(FOLDER, fileName));
              OutputStream outputStream = response.getOutputStream()
        ) {
            response.setHeader("Content-Disposition", "attachment; filename=test.txt");
            log.info("");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            log.info("exception{}", e.getMessage());
        }
    }

    /**
     * excel 无内存溢出
     */
    @GetMapping("/excel")
    public CommonCodeResponse exportExcel(HttpServletResponse response) throws IOException {

        List<DemoExcelDTO> data = fileService.buildExcelData();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "搬入搬出记录";
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcelFactory.write(response.getOutputStream(), DemoExcelDTO.class).sheet("").doWrite(data);
        return CommonDataResponse.success();
    }

    /**
     * 流直接转化成file
     */
    @GetMapping("/stream2file")
    public void exportExcel(MultipartFile multipartFile) throws IOException {
        fileService.fileUpload(multipartFile.getInputStream());
    }
}
