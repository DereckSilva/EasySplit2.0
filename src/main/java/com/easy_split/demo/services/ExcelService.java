package com.easy_split.demo.services;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

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

    public void validExcelExpense(MultipartFile file)  throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        ArrayList<XSSFSheet> sheets = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            for (int j = 0; j < workbook.getSheetAt(i).getPhysicalNumberOfRows(); j++) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // ajustar aqui
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();

                // ajustar aqui
                if (lastRow > 101) throw new CreateExpenseException("Deve-se ter apenas 100 registros para realizar o upload");

                for (int k = firstRow; k < lastRow; k++) {
                    if (k == 0) continue;

                    XSSFRow row = sheet.getRow(k);
                    row.getCell(0);
                }
            }
        }
    }

}
