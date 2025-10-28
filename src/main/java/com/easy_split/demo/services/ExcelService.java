package com.easy_split.demo.services;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

    public XSSFWorkbook exportExcel(String[] sheets, String[][] fields) {

        if (fields.length == 0) throw new CreateExpenseException("Nenhum cabeçalho do excel foi passado");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle style = headerStyle(workbook);
        int index = 0;

        for (int c = index; c < fields.length; c++) {
            XSSFSheet sheet1 = workbook.createSheet(sheets[index]);
            XSSFRow row = sheet1.createRow(0);
            for (int f = 0; f < fields[c].length; f++) {
                XSSFCell cell = row.createCell(f);
                cell.setCellStyle(style);
                cell.setCellValue(fields[c][f]);
            }
            index++;
        }
        return workbook;
    }


    private XSSFCellStyle headerStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(style);
        return style;
    }

    private void addBorders(XSSFCellStyle style) {
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }

}
