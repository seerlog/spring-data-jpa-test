package org.example.springdatajpatest.domain.transaction.repository;

import org.example.springdatajpatest.request.transaction.TransactionSearchRequest;
import org.example.springdatajpatest.response.transaction.TransactionResponse;
import org.springframework.data.domain.Page;

public interface CustomTransactionRepo {
    Page<TransactionResponse> getTransactions(TransactionSearchRequest transactionSearchRequest);
}
