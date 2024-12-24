package com.submaster.submasterserver.api.service.bank;

import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.bank.response.BankFindAllResponse;
import com.submaster.submasterserver.api.service.bank.response.BankFindNameResponse;
import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import com.submaster.submasterserver.entity.bank.Bank;
import com.submaster.submasterserver.entity.bank.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankReadService {

    private static final String NOT_EXIST_BANK_MESSAGE = "존재하지 않는 은행입니다.";

    private final BankRepository bankRepository;

    public Bank findById(int bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new SubMasterException(NOT_EXIST_BANK_MESSAGE));
    }

    public BankFindAllResponse findAll() {
        return BankFindAllResponse.of(bankRepository.findAll().stream()
                .map(BankResponse::of)
                .collect(Collectors.toList()));
    }

    public BankFindNameResponse findByBankNameContaining(String bankName) {
        return BankFindNameResponse.of(bankRepository.findByBankNameContaining(bankName).stream()
                .map(BankResponse::of)
                .collect(Collectors.toList()));
    }

}
