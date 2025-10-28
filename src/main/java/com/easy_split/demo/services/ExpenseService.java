package com.easy_split.demo.services;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.repositories.ExpenseRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        expense.setPayee(expense.getPayee());
        return this.expenseRepository.save(expense);
    }

    public Optional<Expense> getExpense(String identifier) {
        if (identifier.matches("\\d+")) return this.expenseRepository.findById(Integer.parseInt(identifier));
        return this.expenseRepository.findByBarcode(identifier);
    }

    public Map<String, Object> getAllExpenses(int personId) {
        Expense expenses = this.expenseRepository.findByPayeeId(personId);

        Map<String, Object> objExpense = new HashMap<>();
        objExpense.put("expenses", expenses == null ? "" : expenses);
        objExpense.put("countPayments", expenses == null ? "" : expenses.getPayments().stream().toList().size());
        return objExpense;
    }

    public Map<String, Object> getExpenseByPayeeId(int payeeId, int expenseId) {
        Expense expense = this.expenseRepository.findByPayeeIdAndId(payeeId, expenseId);

        Map<String, Object> objExpense = new HashMap<>();
        objExpense.put("expense", expense == null ? "" : expense);
        objExpense.put("payments",  expense == null ? "" : expense.getPayments());
        return objExpense;
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
