package org.example.springdatajpatest.repository;

import org.example.springdatajpatest.domain.transaction.repository.CustomTransactionRepo;
import org.example.springdatajpatest.domain.transaction.repository.TransactionRepository;
import org.example.springdatajpatest.request.transaction.TransactionSearchRequest;
import org.example.springdatajpatest.response.transaction.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestQueryDslConfig.class)
public class TransactionRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryTest.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomTransactionRepo customTransactionRepo;

    @BeforeEach
    public void setUp() {
        transactionRepository.saveAll(TransactionDummy.create());
    }

    @DisplayName("페이징 테스트")
    @Test
    public void pagingTest() {
        TransactionSearchRequest request = new TransactionSearchRequest();
        request.setPageNumber(3);
        request.setPageSize(10);
        Page<TransactionResponse> transactions = customTransactionRepo.getTransactions(request);
        transactions.getContent().forEach(transactionResponse -> logger.info(transactionResponse::toString));
    }
}
