package com.bzetab.equilibra.account.entity;

import com.bzetab.equilibra.owner.entity.Owner;
import com.bzetab.equilibra.account.enums.AccountType;
import com.bzetab.equilibra.account.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currency;

    @Column(name = "is_active")
    private Boolean isActive;

    //Log
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //Relationship mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
}