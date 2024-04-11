package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String testFilePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransactionCSVReader csvReader = new TransactionCSVReader();
        List<Transaction> transactions = csvReader.readTransactions(testFilePath);

        Assertions.assertFalse(transactions.isEmpty(), "Список транзакций пустой");

        Assertions.assertEquals(26, transactions.size(), "Неправильное количество транзакций");

        Transaction firstTransaction = transactions.get(0);
        Assertions.assertEquals("05-12-2023", firstTransaction.getDate(), "Неправильная дата для первой транзакции");
        Assertions.assertEquals(-7850.0, firstTransaction.getAmount(), "Неправильная сумма для первой транзакции");
        Assertions.assertEquals("Сільпо", firstTransaction.getDescription(), "Неправильное описание для первой транзакции");

        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        Assertions.assertEquals("30-01-2024", lastTransaction.getDate(), "Неправильная дата для последней транзакции");
        Assertions.assertEquals(-9100.0, lastTransaction.getAmount(), "Неправильная сумма для последней транзакции");
        Assertions.assertEquals("Інші витрати", lastTransaction.getDescription(), "Неправильное описание для последней транзакции");
    }
}
