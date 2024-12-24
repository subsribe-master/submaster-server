package com.submaster.submasterserver.api.service.account.reseponse;

import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import com.submaster.submasterserver.entity.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountResponse {

    private int id;
    private int userId;
    private BankResponse bank;
    private String accountNumber;

    @Builder
    public AccountResponse(int id, int userId, BankResponse bank, String accountNumber) {
        this.id = id;
        this.userId = userId;
        this.bank = bank;
        this.accountNumber = accountNumber;
    }

    public static AccountResponse of(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .bank(BankResponse.of(account.getBank()))
                .accountNumber(account.getAccountNumber())
                .build();
    }
}
