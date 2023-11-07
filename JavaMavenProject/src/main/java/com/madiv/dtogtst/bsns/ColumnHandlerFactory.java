package com.madiv.dtogtst.bsns;


public class ColumnHandlerFactory {
    private static DateHandlerType dateHandlerType = null;
    private static LongHandlerType longHandlerType = null;
    private static StringHandlerType stringHandlerType = null;
    private static NumericHandlerType numericHandlerType = null;
    
    static {
        dateHandlerType = new DateHandlerType();
        longHandlerType = new LongHandlerType();
        stringHandlerType = new StringHandlerType();
        numericHandlerType = new NumericHandlerType();
    }
    
    
    public static ColumnHandlerType getColumnTypeHandler(ColumnType colType){
        ColumnHandlerType handlerType = null;
        
        switch (colType) {
            case StringColumnMetaData: handlerType = stringHandlerType;
                break;
                
                
            case DateTimeColumnMetaData: handlerType = dateHandlerType;
                break;
                
                
            case LongColumnMetaData: handlerType = longHandlerType;
                break;

                
            case NumericColumnMetaData: handlerType = numericHandlerType;
                break;
                
        }
        
        return handlerType;
    }
}
