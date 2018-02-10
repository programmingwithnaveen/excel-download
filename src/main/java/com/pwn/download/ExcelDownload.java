package com.pwn.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDownload {

	public InputStream downloadFile() {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Cities");

		Object[][] cities = { { "USA", "Dublin", 94568 }, { "USA", "San Ramon", 94568 },
				{ "USA", "Livermore", 97856 }, { "USA", "Pleasanton", 54689 }, };

		int rowCount = 0;

		for (Object[] city : cities) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;

			for (Object field : city) {
				Cell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
			bos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(bos.toByteArray());
	}
}
