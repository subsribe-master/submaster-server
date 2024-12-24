package com.submaster.submasterserver.api.service.bank.response;

import com.submaster.submasterserver.entity.bank.Bank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BankResponse {

    private int id;
    private String bankName;
    private String imagePath;

    @Builder
    public BankResponse(int id, String bankName, String imagePath) {
        this.id = id;
        this.bankName = bankName;
        this.imagePath = imagePath;
    }

    public static BankResponse of(Bank bank) {
        return BankResponse.builder()
                .id(bank.getId())
                .bankName(bank.getBankName())
                .imagePath(bank.getImagePath())
                .build();
    }
}
