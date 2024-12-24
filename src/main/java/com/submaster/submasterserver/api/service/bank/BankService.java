package com.submaster.submasterserver.api.service.bank;

import com.submaster.submasterserver.api.service.bank.request.BankServiceRequest;
import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import com.submaster.submasterserver.entity.bank.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public BankResponse save(BankServiceRequest bankServiceRequest) {
        return BankResponse.of(bankRepository.save(bankServiceRequest.toEntity()));
    }

}
