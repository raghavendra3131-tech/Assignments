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
	    InputStream inputStream = ExcelReader.class.getClassLoader().getResourceAsStream(fileName);
	    if (inputStream == null) {
	        throw new IOException("File not found: " + fileName);
	    }

	    Workbook workbook = WorkbookFactory.create(inputStream);
	    Sheet sheet = workbook.getSheet(sheetName);
	    if (sheet == null) {
	        workbook.close();
	        throw new IOException("Sheet not found: " + sheetName);
	    }

	    List<String[]> credentialsList = new ArrayList<>();

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

	    workbook.close();
	    return credentialsList;
	}


	}



