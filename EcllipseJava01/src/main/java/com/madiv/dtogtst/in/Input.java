package com.madiv.dtogtst.in;

import com.madiv.dtogtst.bsns.ColumnType;

public interface Input {
    public Integer getTotalRecords();
    public ColumnType getColumnType(int position);
    public String getInputLineAt(int position);
}
