package com.selenium.assignment;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 // Adjust the package path accordingly

public class ExcelReader {
	public static List<String[]> getAllCredentials(String fileName, String sheetName) throws IOException {
    List<String[]> credentialsList = new ArrayList<>();

    // Run this method in a background thread from UI to prevent freeze, for example:
    // new Thread(() -> { getAllCredentials("file.xlsx", "Sheet1"); }).start();

    try (InputStream inputStream = ExcelReader.class.getClassLoader().getResourceAsStream(fileName);
         Workbook workbook = WorkbookFactory.create(inputStream)) {
        
        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IOException("Sheet not found: " + sheetName);
        }

        int lastRowIndex = sheet.getLastRowNum();
        // Assuming first row (0) is header, start from 1
        for (int i = 1; i <= lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                credentialsList.add(new String[]{username, password});
            }
        }
    }

    return credentialsList;
}

}
