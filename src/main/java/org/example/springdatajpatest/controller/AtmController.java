package org.example.springdatajpatest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springdatajpatest.response.transaction.TransactionResponse;
import org.example.springdatajpatest.service.AtmService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/atm")
public class AtmController {
    private final AtmService atmService;

    @PostMapping("/search")
    public ResponseEntity<List<TransactionResponse>> searchTransactions() {
        return ResponseEntity.ok(atmService.getTransactions());
    }
}
