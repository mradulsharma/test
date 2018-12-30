package com.madiv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentGmtTime = dateFormat.format(date);
        System.out.println("currentGmtTime =[" + currentGmtTime + "]");

    }

}

