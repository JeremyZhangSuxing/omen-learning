package com.omen.learning.sample.controller;

import com.omen.learning.common.entity.FileInfo;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhang.suxing
 * @date 2020/6/27 10:26
 **/
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final String FOLDER = "F:\\MyTools\\java\\imooc-security\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web";

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
        String fileName = StringUtils.appendIfMissing(name,".xlsx","");
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
    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse httpServletResponse){
        ExcelKit.$Export();
    }
}
