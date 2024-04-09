package com.example;

import java.util.List;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printBottomExpensesForPeriod(List<Transaction> bottomExpenses, String startDate, String endDate) {
        System.out.println("Найменші витрати за" + startDate + " " + endDate);
        for (Transaction transaction : bottomExpenses) {
            System.out.println(transaction);
        }
    }

    public void printTopExpensesForPeriod(List<Transaction> topExpenses, String startDate, String endDate) {
        System.out.println("Найбільші витрати за" + startDate + " " + endDate);
        for (Transaction transaction : topExpenses) {
            System.out.println(transaction);
        }
    }

}
