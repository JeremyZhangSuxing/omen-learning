///*
// * All rights Reserved, Designed By baowei
// *
// * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
// */
//package com.omen.learning.common.utils;
//
//
//import com.weweibuy.framework.common.core.exception.SystemException;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.streaming.SXSSFCell;
//import org.apache.poi.xssf.streaming.SXSSFRow;
//import org.apache.poi.xssf.streaming.SXSSFSheet;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.text.NumberFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * @author yan.zhang
// * @date 2019/4/2 9:37
// */
//public class PoiUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(PoiUtils.class);
//
//    /**
//     * 导出excel文件
//     *
//     * @param fileNamePath 导出的文件名称
//     * @param sheetName    导出的sheet名称
//     * @param list         数据集合
//     * @param titles       表头（excel第一行）
//     * @param fieldNames   字段名称数组(对应对象的属性名成)
//     * @return File文件对象
//     */
//    public static <T> File export(String fileNamePath, String sheetName,
//                                  List<T> list,
//                                  String[] titles,
//                                  String[] fieldNames) throws Exception {
//        SXSSFWorkbook wb = new SXSSFWorkbook();
//        SXSSFSheet sheet = null;
//        // 对每个表生成一个新的sheet,并以表名命名
//        if (sheetName == null) {
//            sheetName = "sheet1";
//        }
//        sheet = wb.createSheet(sheetName);
//        CellStyle style = wb.createCellStyle();
//        // 创建一个居中格式
//        style.setAlignment(HorizontalAlignment.CENTER);
//        // 设置表头的说明
//        SXSSFRow topRow = sheet.createRow(0);
//        for (int i = 0; i < titles.length; i++) {
//            SXSSFCell cell = topRow.createCell(i);
//            cell.setCellValue(titles[i]);
//            cell.setCellStyle(style);
//        }
//        String methodName = "";
//        Method method = null;
//        T t = null;
//        Object ret = null;
//        // 遍历传入集合，通过反射获取字段的get方法
//        for (int i = 0; i < list.size(); i++) {
//            t = list.get(i);
//            SXSSFRow row = sheet.createRow(i + 1);
//            Class<?> clazz = t.getClass();
//            //遍历
//            for (int j = 0; j < fieldNames.length; j++) {
//                methodName = "get" + capitalize(fieldNames[j]);
//                try {
//                    method = clazz.getDeclaredMethod(methodName);
//                } catch (NoSuchMethodException e) {
//                    //不存在该方法，查看父类是否存在
//                    if (null != clazz.getSuperclass()) {
//                        method = clazz.getSuperclass().getDeclaredMethod(methodName);
//                    }
//                }
//                if (null == method) {
//                    throw new SystemException(clazz.getName() + " don't have method --> " + methodName);
//                }
//                ret = method.invoke(t);
//                row.createCell(j).setCellValue((String) ret);
//            }
//        }
//        File file = null;
//        OutputStream os = null;
//        file = new File(fileNamePath);
//        try {
//            os = new FileOutputStream(file);
//            wb.write(os);
//            os.flush();
//        } catch (Exception e) {
//            throw new SystemException("write excel file error!", e);
//        } finally {
//            if (null != os) {
//                os.flush();
//                os.close();
//            }
//        }
//        return file;
//    }
//
//    /**
//     * 将属性值第一个字母转换成大写，返回新字符串
//     * ag：name -> Name
//     */
//    private static String capitalize(final String str) {
//        int strLen;
//        if (str == null || (strLen = str.length()) == 0) {
//            return str;
//        }
//        final char firstChar = str.charAt(0);
//        final char newChar = Character.toTitleCase(firstChar);
//        if (firstChar == newChar) {
//            return str;
//        }
//        char[] newChars = new char[strLen];
//        newChars[0] = newChar;
//        str.getChars(1, strLen, newChars, 1);
//        return String.valueOf(newChars);
//    }
//
//
//    private static Workbook createWorkbook(InputStream inputStream, String fileName) throws IOException {
//        Workbook wookbook = null;
//        if (fileName.endsWith(".xls")) {
//            wookbook = new HSSFWorkbook(inputStream);
//        } else if (fileName.endsWith(".xlsx")) {
//            wookbook = new XSSFWorkbook(inputStream);
//        } else if (fileName.endsWith(".xlsm")) {
//            wookbook = new XSSFWorkbook(inputStream);
//        }
//        return wookbook;
//    }
//
//    private static Object getCellValue(Cell cell, Field field, int count) {
//        switch (cell.getCellType()) {
//            case STRING:
//                return FieldReflectionUtil.formatStringValue(field, cell.getStringCellValue());
//            case NUMERIC:
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    Date value = cell.getDateCellValue();
//                    return FieldReflectionUtil.formatDateValue(field, value);
//                } else {
//
//                    double value = cell.getNumericCellValue();
//
//                    return FieldReflectionUtil.formatDoubleValue(field, value);
//
//                }
//            case BLANK:
//                return null;
//            case BOOLEAN:
//                return FieldReflectionUtil.formatBooleanValue(field, cell.getBooleanCellValue());
//            case FORMULA:
//                return null;
//            default:
//                throw new ClientException("Excel单元格第: " + count + " 行找不到正确的解析格式");
//        }
//    }
//
//
//    private static Object getCellValue(Cell cell) {
//        Object value = null;
//        switch (cell.getCellType()) {
//            case STRING:
//                value = cell.getStringCellValue();
//                break;
//            case NUMERIC:
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                } else if (cell.getCellStyle().getDataFormat() == 58) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                    //yyyy-MM-dd
//                } else if (cell.getCellStyle().getDataFormat() == 14) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                    //yyyy年m月d日
//                } else if (cell.getCellStyle().getDataFormat() == 31) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                    //yyyy年m月
//                } else if (cell.getCellStyle().getDataFormat() == 57) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                    //HH:mm
//                } else if (cell.getCellStyle().getDataFormat() == 20) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                    //h时mm分
//                } else if (cell.getCellStyle().getDataFormat() == 32) {
//                    value = simpleDateFormat.format(cell.getDateCellValue());
//                    break;
//                } else {
//                    NumberFormat nf = NumberFormat.getInstance();
//                    //对于 1 POI会自动解析成1.0 针对这种情况做对应处理
//                    value = nf.format(cell.getNumericCellValue());
//                    if (((String) value).contains(",")) {
//                        value = ((String) value).replace(",", "");
//                    }
//                    break;
//                }
//            case BLANK:
//                value = cell.getDateCellValue();
//                break;
//            case BOOLEAN:
//                value = cell.getBooleanCellValue();
//                break;
//            case FORMULA:
//                value = "";
//                break;
//            default:
//                //记录没有正确匹配到行号与列数，便于排查
//                logger.event("imp exception!").msg("Excel解析错误");
//        }
//        return value;
//    }
//
//
//    /**
//     * @param inputStream 不能为空
//     * @param keys
//     * @param fileName
//     * @return
//     * @throws Exception
//     */
//    public static List<Map<String, Object>> imp(FileInputStream inputStream, String[] keys, String fileName) throws Exception {
//        if (Objects.isNull(inputStream)) {
//            throw new SystemException("inputStream can not be null!");
//        }
//
//        List<Map<String, Object>> list = new ArrayList<>(1024);
//        Map<String, Object> map;
//        Workbook wookbook = null;
//        try {
//            wookbook = createWorkbook(inputStream, fileName);
//            // 获取第一个工作表信息
//            Sheet sheet = Objects.requireNonNull(wookbook).getSheetAt(0);
//            //获得数据的总行数
//            int totalRowNum = sheet.getLastRowNum();
//            // 获得表头
//            Row rowHead = sheet.getRow(0);
//            // 获得表头总列数
//            int cols = rowHead.getPhysicalNumberOfCells();
//            // 传入的key数组长度与表头长度不一致
//            if (keys.length != cols) {
//                throw new SystemException("keys length does not match head row's cols!");
//            }
//            Row row = null;
//            Cell cell = null;
//            Object value = null;
//            // 遍历所有行
//            for (int i = 1; i <= totalRowNum; i++) {
//                // 清空数据，避免遍历时读取上一次遍历数据
//                row = null;
//                cell = null;
//                value = null;
//                map = new LinkedHashMap<>(1024);
//                row = sheet.getRow(i);
//                // 若是空行进行下一次循环
//                if (null == row) continue;
//                // 遍历该行所有列
//                for (short j = 0; j < cols; j++) {
//                    cell = row.getCell(j);
//                    // 为空时，continue下一列
//                    if (null == cell) continue;
//                    // 根据poi返回的类型，做相应的get处理
//                    value = getCellValue(cell);
//                    map.put(keys[j], value);
//                }
//                list.add(map);
//            }
//        } catch (Exception e) {
//            throw new SystemException("parse excel exception!", e);
//        } finally {
//            if (null != inputStream) {
//                inputStream.close();
//            }
//        }
//        return list;
//    }
//
//
//    /**
//     * @param file excel文件对象
//     * @param keys 字段名称数组
//     */
//    public static List<Map<String, Object>> imp(File file, String[] keys) throws Exception {
//        if (null == keys || file == null) {
//            throw new SystemException("keys or file can not be null!");
//        }
//        //excel文件扩展名检查
//        String fileName = file.getName();
//        if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx") && !fileName.endsWith(".xlsm")) {
//            throw new SystemException("The file is not excel document!");
//        }
//        return imp(new FileInputStream(file), keys, fileName);
//    }
//
//
//    /**
//     * @param fileInputStream 不能为空
//     * @param fileName        不能为空
//     * @param clazz           不能为空
//     * @param sheetAt         第几个 sheet
//     * @param titleLength     标题所占行数; 标题所占的行将被忽略
//     * @return
//     */
//    public static <T> List<T> importExcl(InputStream fileInputStream, String fileName, Class<T> clazz,
//                                         Integer sheetAt, Integer titleLength) {
//        return importExcl(fileInputStream, fileName, clazz, sheetAt, titleLength, null);
//    }
//
//
//    /**
//     * @param fileInputStream 不能为空
//     * @param fileName        不能为空
//     * @param clazz           不能为空; 只能传递 object 的子类
//     * @param sheetAt         第几个 sheet
//     * @param titleLength     标题所占行数; 标题所占的行将被忽略
//     * @param validator       对象验证器
//     * @return
//     */
//    public static <T> List<T> importExcl(InputStream fileInputStream, String fileName, Class<T> clazz,
//                                         Integer sheetAt, Integer titleLength, Validator validator) {
//        try {
//            Workbook workbook = createWorkbook(fileInputStream, fileName);
//            Sheet sheet = workbook.getSheetAt(sheetAt);
//            Iterator<Row> rowIterator = sheet.rowIterator();
//            List<Field> fieldList = new ArrayList<Field>();
//
//            getFields(fieldList, clazz);
//
//            int count = 0;
//            List objectList = new ArrayList<T>(50);
//
//            while (rowIterator.hasNext()) {
//                Row rowX = rowIterator.next();
//                count++;
//                // 忽略标题
//                if (count <= titleLength) {
//                    continue;
//                }
//                Object rowObj = clazz.newInstance();
//                for (int i = 0; i < fieldList.size(); i++) {
//                    Cell cell = rowX.getCell(i);
//                    Object value = null;
//                    Field field = fieldList.get(i);
//                    if (Objects.nonNull(cell)) {
//                        value = getCellValue(cell, field, count);
//                    }
//                    field.setAccessible(true);
//                    field.set(rowObj, value);
//                    field.setAccessible(false);
//                }
//                if (validator != null) {
//                    Set<ConstraintViolation<Object>> validate = validator.validate(rowObj);
//                    for (ConstraintViolation violation : validate) {
//                        throw new ExcelValidateException("第:" + count + "行," + violation.getMessage(), "-400");
//                    }
//                }
//                objectList.add(rowObj);
//            }
//            return objectList;
//        } catch (IOException e) {
//            logger.event("解析excel")
//                    .msg("解析excel IO异常:{}", e.getMessage())
//                    .exception(e)
//                    .error();
//            throw new SystemException("解析excel IO异常", "999999999", e);
//        } catch (ReflectiveOperationException e) {
//            logger.event("解析excel")
//                    .msg("excel 解析并封装对象时反射异常: {}", e.getMessage())
//                    .exception(e)
//                    .error();
//            throw new SystemException("excel解析并封装对象时反射异常", "999999999", e);
//        } finally {
//            try {
//                fileInputStream.close();
//            } catch (IOException e) {
//                logger.msg("关闭流异常")
//                        .exception(e)
//                        .error();
//            }
//        }
//    }
//
//    private static void getFields(List list, Class clazz) {
//        for (Field field : clazz.getDeclaredFields()) {
//            if (Modifier.isStatic(field.getModifiers())) {
//                continue;
//            }
//            list.add(field);
//        }
//        Class superclass = clazz.getSuperclass();
//        String simpleName = superclass.getSimpleName();
//        if (superclass instanceof Object && simpleName.equals(Object.class.getSimpleName())) {
//            return;
//        } else {
//            getFields(list, superclass);
//        }
//    }
//
//
//}
