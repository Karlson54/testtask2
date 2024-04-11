package com.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransactionCSVReader csvReader = new TransactionCSVReader();
        List<Transaction> transactions = csvReader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear);
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "01-12-2023";
        String endDate = "10-01-2024";
        List<Transaction> bottomExpenses = analyzer.findBottomExpensesForPeriod(startDate, endDate);
        reportGenerator.printBottomExpensesForPeriod(bottomExpenses, startDate, endDate);

        List<Transaction> TopExpenses = analyzer.findTopExpensesForPeriod(startDate, endDate);
        reportGenerator.printTopExpensesForPeriod(TopExpenses, startDate, endDate);

        ExpenseReportGenerator textReportGenerator = new ExpenseReportGenerator(transactions);
        
        textReportGenerator.generateReport("TheLastTask.txt");
    }
}
