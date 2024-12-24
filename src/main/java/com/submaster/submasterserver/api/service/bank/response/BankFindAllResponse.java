package com.submaster.submasterserver.api.service.bank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BankFindAllResponse {

    private List<BankResponse> bankResponses;

    @Builder
    public BankFindAllResponse(List<BankResponse> bankResponses) {
        this.bankResponses = bankResponses;
    }

    public static BankFindAllResponse of(List<BankResponse> bankResponses) {
        return BankFindAllResponse.builder()
                .bankResponses(bankResponses)
                .build();
    }
}
