package br.com.excelreader.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelReaderService {

    public String upload(MultipartFile file, Integer numberOfSheet) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

        if (numberOfSheet == null || numberOfSheet < 0) numberOfSheet = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheet; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            System.out.println("=> " + sheet.getSheetName());

            DataFormatter dataFormatter = new DataFormatter();

            System.out.println("Iterating over Rows and Columns using Iterator");
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.println(dataFormatter.formatCellValue(cell) + "\t");
                    System.out.println();
                }
            }
        }
        return "OK";
    }
}
