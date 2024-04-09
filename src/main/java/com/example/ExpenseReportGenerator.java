package com.example;

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

    public void generateReport() {
        Map<String, Double> categoryExpenses = new HashMap<>();
        Map<String, Double> monthExpenses = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String category = transaction.getDescription();
            String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));

            categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + transaction.getAmount());
            monthExpenses.put(month, monthExpenses.getOrDefault(month, 0.0) + transaction.getAmount());
        }

        System.out.println("Звіт про витрати:");
        System.out.println("Сумарні витрати по категоріям:");

        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            double amount = entry.getValue();
            
            int symbolCount = (int) Math.abs(amount / 1000);
            System.out.println(category + ": " + "*".repeat(symbolCount));
        }
    }
}