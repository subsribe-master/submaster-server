package com.submaster.submasterserver.api.service.account;

import com.submaster.submasterserver.api.service.account.request.AccountServiceRequest;
import com.submaster.submasterserver.api.service.account.reseponse.AccountResponse;
import com.submaster.submasterserver.api.service.bank.BankReadService;
import com.submaster.submasterserver.entity.account.AccountRepository;
import com.submaster.submasterserver.entity.bank.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final BankReadService bankReadService;

    public AccountResponse save(AccountServiceRequest accountServiceRequest) {
        Bank bank = bankReadService.findById(accountServiceRequest.getBankId());
        return AccountResponse.of(accountRepository.save(accountServiceRequest.toEntity(bank)));
    }
}
