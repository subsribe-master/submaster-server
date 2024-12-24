package com.submaster.submasterserver.api.service.account.reseponse;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountFindUserResponse {

    private List<AccountResponse> account;

    @Builder
    public AccountFindUserResponse(List<AccountResponse> account) {
        this.account = account;
    }

    public static AccountFindUserResponse of(List<AccountResponse> account) {
        return AccountFindUserResponse.builder()
                .account(account)
                .build();
    }
}
