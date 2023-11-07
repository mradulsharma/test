package com.madiv.dtogtst.bsns;

import java.util.List;

import org.apache.log4j.Logger;

import com.madiv.generic.error.MsgCodeImpl;
import com.madiv.generic.error.MsgHelper;


public class LongHandlerType extends ColumnHandlerType {
    final static Logger LOG = Logger.getLogger(LongHandlerType.class);
    private String[] columnDefinition = {
            "@XmlTransient",
            "public LongColumn get%1$sColumn() {",
            "    return (LongColumn) this.column(%1$s);",
            "}",
            " ",
            "public Long get%1$s() {",
            "    return (Long) this.values[%1$s];",
            "}",
            " ",
            "public void set%1$s(Long value) {",
            "    if (COLUMN_METADATA[%1$s].isValid(value)) this.values[%2$s] = value;",
            "}",
            " "
    };

    public List<String> generateColumnDefinition(String columnDeclarationString, int beginIndex) {
        LOG.info(MsgHelper.getMessage(MsgCodeImpl.MADIV_08, this.getClass().getSimpleName()));
        super.setColumnDefinition(columnDefinition);
        return super.generateColumnDefinition(columnDeclarationString, beginIndex);
    }

}
