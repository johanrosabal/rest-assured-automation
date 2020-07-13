package utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExcelUtils {

    private static XSSFSheet sheet2;
    private static HSSFSheet sheet1;

    private boolean oldFormat;

    public ExcelUtils(String excelFilename, String sheetName){
        Path resourceDirectory = Paths.get("src", "test", "resources", "excel");
        String resourcesExcel = resourceDirectory.toFile().getAbsolutePath() + "\\";
        if(excelFilename.contains(".xlsx")){
            try {
                oldFormat = false;
                XSSFWorkbook workbook2 = new XSSFWorkbook(resourcesExcel + excelFilename);
                sheet2 = workbook2.getSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                oldFormat = true;
                InputStream file = new FileInputStream(resourcesExcel +excelFilename);
                HSSFWorkbook workbook1 = new HSSFWorkbook(new POIFSFileSystem(file));
                sheet1 = workbook1.getSheet(sheetName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getCellData(int row, int column){
        DataFormatter formatter = new DataFormatter();
        if(oldFormat){
            return formatter.formatCellValue(sheet1.getRow(row).getCell(column));
        }else{
            return formatter.formatCellValue(sheet2.getRow(row).getCell(column));
        }
    }

    public int getRowCount(){
        int rowsCount;
        if(oldFormat){
            rowsCount = sheet1.getPhysicalNumberOfRows();
        }else{
            rowsCount = sheet2.getPhysicalNumberOfRows();
        }
        return rowsCount;
    }
}
