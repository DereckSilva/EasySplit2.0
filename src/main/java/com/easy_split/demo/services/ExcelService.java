package com.easy_split.demo.services;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ExcelService {

    public XSSFWorkbook exportExcel(String[] sheets, String[][] fields) {

        if (fields.length == 0) throw new CreateExpenseException("Nenhum cabeçalho do excel foi passado");

        XSSFWorkbook workbook = new XSSFWorkbook();
        Integer index = 0;

        for (int c = index; c < fields.length; c++) {
            XSSFSheet sheet1 = workbook.createSheet(sheets[index]);
            XSSFRow row = sheet1.createRow(0);
            for (int f = 0; f < fields[c].length; f++) {
                XSSFCell cell = row.createCell(f);
                cell.setCellValue(fields[c][f]);
            }
            index++;
        }
        return workbook;
    }

    public String validExcelExpense() {
        return "string";
    }

}
