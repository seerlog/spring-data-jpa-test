package org.example.springdatajpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springdatajpatest.domain.transaction.repository.TransactionRepository;
import org.example.springdatajpatest.response.transaction.TransactionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AtmService {
    private final TransactionRepository transactionRepository;

    public List<TransactionResponse> getTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionResponse::of)
                .toList();
    }

}
