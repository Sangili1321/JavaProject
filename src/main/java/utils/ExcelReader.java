package utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public int getRowNum(XSSFSheet sheet) throws IOException {

        var rowCount = sheet.getLastRowNum();
        return rowCount;
    }

    public int getColumnNum(XSSFRow row) throws IOException {

        var cellCount = row.getLastCellNum();
        return cellCount;
    }

    public String[][] getData(String path) throws IOException {
        XSSFSheet sheet;
        try (XSSFWorkbook book = new XSSFWorkbook(path)) {
            sheet = book.getSheetAt(0);
        }

        var rows = getRowNum(sheet);
        String [][] data = new String[6][2];
        for (int i = 1; i < rows; i++) {
            var row = sheet.getRow(i);
            System.out.println(row.getCell(0).toString());
            data[i-1][0] = row.getCell(0).toString();
            System.out.println(row.getCell(1).toString());
            data[i-1][1] = row.getCell(1).toString();

        }

        return data;

    }
}
