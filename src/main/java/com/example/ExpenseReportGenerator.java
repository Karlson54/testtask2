package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseReportGenerator {
    private List<Transaction> transactions;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ExpenseReportGenerator(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void generateReport(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Map<String, Double> categoryExpenses = new HashMap<>();
            Map<String, Double> monthExpenses = new HashMap<>();

            for (Transaction transaction : transactions) {
                LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
                String category = transaction.getDescription();
                String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

                categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + transaction.getAmount());
                monthExpenses.put(month, monthExpenses.getOrDefault(month, 0.0) + transaction.getAmount());
            }

            writer.write("Звіт про витрати:\n");
            writer.write("Сумарні витрати по категоріям:\n");

            for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
                String category = entry.getKey();
                double amount = entry.getValue();
                
                int symbolCount = (int) Math.abs(amount / 1000);
                writer.write(category + ": " + "*".repeat(symbolCount) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}