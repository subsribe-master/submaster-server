package com.submaster.submasterserver.api.controller.account.request;

import com.submaster.submasterserver.api.service.account.request.AccountServiceRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRequest {

    @Positive(message = "사용자 정보는 필수입니다.")
    private int userId;
    @Positive(message = "은행 정보는 필수입니다.")
    private int bankId;
    private String accountNumber;

    @Builder
    public AccountRequest(int userId, int bankId, String accountNumber) {
        this.userId = userId;
        this.bankId = bankId;
        this.accountNumber = accountNumber;
    }

    public AccountServiceRequest toServiceRequest() {
        return AccountServiceRequest.builder()
                .userId(userId)
                .bankId(bankId)
                .accountNumber(accountNumber)
                .build();
    }
}
