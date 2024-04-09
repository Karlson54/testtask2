package com.example;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private List<Transaction> transactions;
    private DateTimeFormatter dateFormatter;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Transaction> findBottomExpensesForPeriod(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        List<Transaction> expensesInPeriod = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), dateFormatter);
            if (transactionDate.isAfter(startDate.minusDays(1)) && transactionDate.isBefore(endDate.plusDays(1))) {
                if (transaction.getAmount() < 0) {
                    expensesInPeriod.add(transaction);
                }
            }
        }
        expensesInPeriod.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
        return expensesInPeriod;
    }

    public List<Transaction> findTopExpensesForPeriod(String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        List<Transaction> expensesInPeriod = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), dateFormatter);
            if (transactionDate.isAfter(startDate.minusDays(1)) && transactionDate.isBefore(endDate.plusDays(1))) {
                if (transaction.getAmount() < 0) {
                    expensesInPeriod.add(transaction);
                }
            }
        }
        expensesInPeriod.sort(Comparator.comparingDouble(Transaction::getAmount));
        return expensesInPeriod;
    }
}