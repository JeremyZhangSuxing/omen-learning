package com.omen.learning.sample.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.omen.learning.common.entity.FileInfo;
import com.omen.learning.sample.file.FileService;
import com.omen.learning.sample.support.DemoExcelDTO;
import com.omen.learning.sample.support.DemoExcelUploadDTO;
import com.omen.learning.sample.support.UploadDataListener;
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
    @GetMapping("/excel/download")
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
     */
    @PostMapping("/excel/upload")
    @ResponseBody
    public CommonCodeResponse upload(MultipartFile file) throws IOException {
        EasyExcelFactory.read(file.getInputStream(), DemoExcelUploadDTO.class, new UploadDataListener()).sheet().doRead();
        return CommonCodeResponse.success();
    }

}
