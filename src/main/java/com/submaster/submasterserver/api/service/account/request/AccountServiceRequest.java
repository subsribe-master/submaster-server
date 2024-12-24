package com.submaster.submasterserver.api.service.account.request;

import com.submaster.submasterserver.entity.account.Account;
import com.submaster.submasterserver.entity.account.UseYn;
import com.submaster.submasterserver.entity.bank.Bank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountServiceRequest {

    private int userId;
    private int bankId;
    private String accountNumber;

    @Builder
    public AccountServiceRequest(int userId, int bankId, String accountNumber) {
        this.userId = userId;
        this.bankId = bankId;
        this.accountNumber = accountNumber;
    }

    public Account toEntity(Bank bank) {
        return Account.builder()
                .userId(userId)
                .bank(bank)
                .accountNumber(accountNumber)
                .useYn(UseYn.YES)
                .build();
    }
}
