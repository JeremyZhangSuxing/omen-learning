package com.omen.learning.sample.support;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author : Knight
 * @date : 2020/12/15 7:07 下午
 */
public class BillTypeConverter implements Converter<BillType> {
    @Override
    public Class<BillType> supportJavaTypeKey() {
        return BillType.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public BillType convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return BillType.B2C;
    }

    @Override
    public CellData<String> convertToExcelData(BillType value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData<>(value.toString());
    }
}
