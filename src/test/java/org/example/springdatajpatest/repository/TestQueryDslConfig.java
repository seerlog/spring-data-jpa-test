package org.example.springdatajpatest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springdatajpatest.domain.transaction.repository.CustomTransactionRepo;
import org.example.springdatajpatest.domain.transaction.repository.CustomTransactionRepoImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@TestConfiguration
public class TestQueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public CustomTransactionRepo customTransactionRepo(JPAQueryFactory jpaQueryFactory) {
        return new CustomTransactionRepoImpl(jpaQueryFactory);
    }
}
