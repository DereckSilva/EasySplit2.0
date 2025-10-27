package com.easy_split.demo.controllers;

import com.easy_split.demo.controllers.error.CreateExpenseException;
import com.easy_split.demo.dtos.requests.expense.AllExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.expense.ExpenseRequestDTO;
import com.easy_split.demo.dtos.response.ExpenseResponseDTO;
import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.mappers.ExpenseMapper;
import com.easy_split.demo.services.ExcelService;
import com.easy_split.demo.services.ExpenseProcessingService;
import com.easy_split.demo.services.ExpenseService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    public ExpenseService expenseService;

    @Autowired
    public ExpenseProcessingService configurationExpenseService;

    @Autowired
    public ExcelService excelService;

    @GetMapping("/export-excel")
    public HttpServletResponse  exportExcelExpense(HttpServletResponse response) throws IOException {

        String[] fieldsExpense = {"Expense Identifier", "Name", "Price", "Parcels", "Intermediary", "Paid", "Maturity", "Payee", "Intermediaries"};
        String[] fieldsPayment = {"Person", "Expense Identifier", "Total Payment", "Paid"};
        String[][] fields      = {fieldsExpense, fieldsPayment};
        String[] sheets        = {"Expense", "Payments"};
        XSSFWorkbook workBook = this.excelService.exportExcel(sheets, fields);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=expense.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workBook.write(outputStream);
        workBook.close();
        outputStream.close();

        return response;
    }

    @PostMapping("/upload-excel")
    public String uploadExcelExpense(@RequestParam(value = "file", required = false) MultipartFile file)  throws IOException {

        if (file == null) throw new CreateExpenseException("O parâmetro 'file' é obrigatório. Envie o arquivo com a chave 'file'.");

        this.excelService.validExcelExpense(file);
        return "salve";
    }

    @PostMapping("/create")
    public String createExpense(@RequestBody @Valid CreateExpenseRequestDTO expenseRequestDTO){
        CreateExpenseDTO createExpense = this.configurationExpenseService.configExpense(expenseRequestDTO);
        Expense expense = ExpenseMapper.toEntity(createExpense);
        Expense createdExpense = expenseService.createExpense(expense);

        // parcial
        Payments payments = this.configurationExpenseService.configPayment(expense.getPayee(), createdExpense, expenseRequestDTO.getPayment());

        ExpenseResponseDTO expenseResp = ExpenseMapper.toDTO(createdExpense);

        return "testee";
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllExpensesByPayeeId(@RequestBody @Valid AllExpenseRequestDTO payee) {
        Map<String, Object> expenses =  this.expenseService.getAllExpenses(payee.getPayeeId());
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping("/one")
    public ResponseEntity<Map<String, Object>> getExpenseByPayeeByExpense(@RequestBody @Valid ExpenseRequestDTO expense) {
        Person payee = this.configurationExpenseService.getPersonRelatedExpense(expense.getPayee());
        Map<String, Object> findExpense = this.expenseService.getExpenseByPayeeId(payee.getId(), expense.getExpenseId());
        return ResponseEntity.status(HttpStatus.OK).body(findExpense);
    }

}
