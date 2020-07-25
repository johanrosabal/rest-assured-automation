package com.blacksystem.automation.application.utils;

public class ExcelUtilsTest {

    public static void main(String[] args) {
        ExcelUtils excelUtils = new ExcelUtils("TestData.xls","Test");

        System.out.println("Number of Rows: "+excelUtils.getRowCount());
        System.out.println(excelUtils.getCellData(1,0));
    }
}
