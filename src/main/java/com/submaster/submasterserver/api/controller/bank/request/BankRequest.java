package com.submaster.submasterserver.api.controller.bank.request;

import com.submaster.submasterserver.api.service.bank.request.BankServiceRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BankRequest {

    @NotNull(message = "은행명은 필수입니다.")
    private String bankName;
    private String imagePath;

    @Builder
    public BankRequest(String bankName, String imagePath) {
        this.bankName = bankName;
        this.imagePath = imagePath;
    }

    public BankServiceRequest toServiceRequest() {
        return BankServiceRequest.builder()
                .bankName(bankName)
                .imagePath(imagePath)
                .build();
    }
}
