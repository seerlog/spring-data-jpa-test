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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@Import(TestQueryDslConfig.class)
public class TransactionRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryTest.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomTransactionRepo customTransactionRepo;

    @DisplayName("거래내역 페이징 테스트")
    @Test
    public void transactionPagingTest() {
        // given
        transactionRepository.saveAll(TransactionDummy.create());

        TransactionSearchRequest request = TransactionSearchRequest.builder()
                .pageNumber(3)
                .pageSize(10)
                .build();

        // when
        Page<TransactionResponse> transactions = customTransactionRepo.getTransactions(request);

        // then
        assertEquals(31, transactions.getContent().get(0).getNo());
        assertEquals(40, transactions.getContent().get(9).getNo());
    }
}
