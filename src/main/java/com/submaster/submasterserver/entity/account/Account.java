package com.submaster.submasterserver.entity.account;

import com.submaster.submasterserver.entity.BaseTimeEntity;
import com.submaster.submasterserver.entity.bank.Bank;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    private int userId;

    private String accountNumber;

    private UseYn useYn;

    @Builder
    public Account(int id, Bank bank, int userId, String accountNumber, UseYn useYn) {
        this.id = id;
        this.bank = bank;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.useYn = useYn;
    }
}
