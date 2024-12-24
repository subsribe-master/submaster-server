package com.submaster.submasterserver.api.service.bank.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BankFindNameResponse {

    private List<BankResponse> bankResponses;

    @Builder
    public BankFindNameResponse(List<BankResponse> bankResponses) {
        this.bankResponses = bankResponses;
    }

    public static BankFindNameResponse of(List<BankResponse> bankResponses) {
        return BankFindNameResponse.builder()
                .bankResponses(bankResponses)
                .build();
    }
}
