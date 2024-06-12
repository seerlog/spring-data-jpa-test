package org.example.springdatajpatest.repository;

import org.example.springdatajpatest.domain.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionDummy {
    public static List<Transaction> create() {
        return List.of(
                Transaction.builder()
                        .name("홍길동")
                        .city("서울")
                        .storeName("GS25")
                        .price(1000)
                        .tradeDt(LocalDateTime.now())
                        .state("Y")
                        .creator("홍길동")
                        .createdDt(LocalDateTime.now())
                        .isDeleted("N")
                        .build()
        );
    }
}
