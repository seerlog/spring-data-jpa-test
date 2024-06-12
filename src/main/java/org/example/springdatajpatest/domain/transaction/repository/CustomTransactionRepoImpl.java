package org.example.springdatajpatest.domain.transaction.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.springdatajpatest.request.transaction.TransactionSearchRequest;
import org.example.springdatajpatest.response.transaction.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.springdatajpatest.domain.transaction.QTransaction.transaction;

@Repository
@RequiredArgsConstructor
public class CustomTransactionRepoImpl implements CustomTransactionRepo {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<TransactionResponse> getTransactions(TransactionSearchRequest request) {
        List<TransactionResponse> transactionResponses = jpaQueryFactory
                .select(Projections.bean(TransactionResponse.class,
                        transaction.no,
                        transaction.name,
                        transaction.city,
                        transaction.storeName,
                        transaction.price,
                        transaction.tradeDt,
                        transaction.state))
                .from(transaction)
                .where(transaction.isDeleted.eq("N"))
                .offset(request.getOffset())
                .limit(request.getLimit())
                .fetch();

        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());

        Long totalCount = jpaQueryFactory
                .select(transaction.count())
                .from(transaction)
                .where(transaction.isDeleted.eq("N"))
                .fetchOne();

        return new PageImpl<>(transactionResponses, pageable, totalCount);
    }
}
