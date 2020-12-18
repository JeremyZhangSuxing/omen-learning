package com.omen.learning.sample.file;

import com.google.common.collect.Lists;
import com.omen.learning.sample.support.BillType;
import com.omen.learning.sample.support.DemoExcelDTO;
import com.weweibuy.framework.common.core.exception.BusinessException;
import com.weweibuy.framework.common.core.model.eum.CommonErrorCodeEum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class FileService {

    /**
     * 构建建议的excel mapping javaBean
     */
    public List<DemoExcelDTO> buildExcelData() {
        List<DemoExcelDTO> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            DemoExcelDTO demoExcelDTO = new DemoExcelDTO();
            demoExcelDTO.setBillType(BillType.B2C);
            demoExcelDTO.setDispatchDate(System.currentTimeMillis());
            demoExcelDTO.setUsername("knight");
            data.add(demoExcelDTO);
        }
        return data;
    }

    /**
     * 临时在容器内生成文件作为附件使用
     * 新产生的会覆盖的存在的适合单线程使用
     */
    public void fileUpload(InputStream inputStream) throws IOException {
        String path = Optional.ofNullable(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource(""))
                .orElseThrow(() -> new BusinessException(CommonErrorCodeEum.BAD_REQUEST_PARAM)).getPath();
        File file = new File(path+"su.pdf");
        FileUtils.copyInputStreamToFile(inputStream, file);
        log.error("生成文件在磁盘上的全路径 {}" ,path +"su.pdf");
    }
}
