package com.omen.learning.common.utils;

import com.github.houbb.csv.support.writer.impl.CsvWriterConsole;
import com.github.houbb.csv.util.CsvHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 导出CSV 数据
 *
 * @author durenhao
 * @date 2019/8/19 10:42
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvUtils {
    public static <T> void export(List<T> body, OutputStream outputStream) throws IOException {
        List<String> lines = CsvHelper.write(body, new CsvWriterConsole(), true);
        for (String line : lines) {
            outputStream.write(line.getBytes(StandardCharsets.UTF_8));
        }
    }

}
