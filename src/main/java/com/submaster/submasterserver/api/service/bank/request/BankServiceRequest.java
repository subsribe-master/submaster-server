package com.submaster.submasterserver.api.service.bank.request;

import com.submaster.submasterserver.entity.bank.Bank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BankServiceRequest {

    private String bankName;
    private String imagePath;

    @Builder
    public BankServiceRequest(String bankName, String imagePath) {
        this.bankName = bankName;
        this.imagePath = imagePath;
    }

    public Bank toEntity() {
        return Bank.builder()
                .bankName(bankName)
                .imagePath(imagePath)
                .build();
    }
}
