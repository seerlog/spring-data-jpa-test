package org.example.springdatajpatest.repository;

import org.example.springdatajpatest.domain.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDummy {
    public static List<Transaction> create() {
        List<Transaction> transactions = new ArrayList<>();

        for(int i=1;i<=100;i++) {
            transactions.add(Transaction.builder()
                    .name("홍길동" + i)
                    .city("서울")
                    .storeName("GS25")
                    .price(1000 + 1)
                    .tradeDt(LocalDateTime.now())
                    .state("Y")
                    .creator("홍길동" + 1)
                    .createdDt(LocalDateTime.now())
                    .isDeleted("N")
                    .build());
        }

        return transactions;
    }
}
