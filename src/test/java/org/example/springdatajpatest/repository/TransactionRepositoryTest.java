package org.example.springdatajpatest.repository;

import org.example.springdatajpatest.domain.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class TransactionRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryTest.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindAll() {
        transactionRepository.findAll().forEach(transaction -> {
            logger.info(transaction::toString);
        });
    }
}
