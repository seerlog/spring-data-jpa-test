package org.example.springdatajpatest.domain.transaction;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "TRAN")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO", nullable = false)
    private Long no;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STORE_NAME", nullable = false)
    private String storeName;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "TRADE_DT", nullable = false)
    private LocalDateTime tradeDt;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "CREATOR", nullable = false)
    private String creator;

    @Column(name = "MODIFIER")
    private String modifier;

    @CreationTimestamp
    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DT")
    private LocalDateTime modifiedDt;

    @Column(name = "IS_DELETED", nullable = false)
    private String isDeleted;
}
