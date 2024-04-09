package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        double result = analyzer.calculateTotalBalance();

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        int countFeb = analyzer.countTransactionsByMonth("02-2023");
        int countMar = analyzer.countTransactionsByMonth("03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction transaction1 = new Transaction("2023-12-05", -7850.0, "Сільпо");
        Transaction transaction2 = new Transaction("2023-12-07", -1200.0, "Аптека");
        Transaction transaction3 = new Transaction("2023-12-10", 80000.0, "Зарплата");

        List<Transaction> transactions = List.of(transaction1, transaction2, transaction3);
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaction> topExpenses = analyzer.findTopExpenses();

        Assertions.assertEquals(2, topExpenses.size(), "Неправильное количество наибольших витрат");
        Assertions.assertEquals(-7850.0, topExpenses.get(0).getAmount(), "Неправильная величина наибольшей витраты");
        Assertions.assertEquals(-1200.0, topExpenses.get(1).getAmount(),
                "Неправильная величина второй наибольшей витраты");
    }
}