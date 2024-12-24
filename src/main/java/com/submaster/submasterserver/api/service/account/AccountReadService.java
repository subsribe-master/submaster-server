package com.submaster.submasterserver.api.service.account;

import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.account.reseponse.AccountFindUserResponse;
import com.submaster.submasterserver.api.service.account.reseponse.AccountResponse;
import com.submaster.submasterserver.entity.account.Account;
import com.submaster.submasterserver.entity.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountReadService {

    private static final String NOT_EXIST_ACCOUNT_MESSAGE = "존재하지 않는 계좌정보입니다.";

    private final AccountRepository accountRepository;

    public Account findById(int accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new SubMasterException(NOT_EXIST_ACCOUNT_MESSAGE));
    }

    public AccountFindUserResponse findByUserId(int userId) {
        return AccountFindUserResponse.of(
                accountRepository.findByUserId(userId).stream()
                        .map(AccountResponse::of)
                        .toList()
        );
    }
}
