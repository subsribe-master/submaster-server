package com.submaster.submasterserver.api.service.account;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.service.account.request.AccountServiceRequest;
import com.submaster.submasterserver.api.service.account.reseponse.AccountResponse;
import com.submaster.submasterserver.entity.account.Account;
import com.submaster.submasterserver.entity.account.AccountRepository;
import com.submaster.submasterserver.entity.bank.Bank;
import com.submaster.submasterserver.entity.bank.BankRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class AccountServiceTest extends IntegrationTestSupport {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountReadService accountReadService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;

    @AfterEach
    void tearDown() {
        accountRepository.deleteAllInBatch();
        bankRepository.deleteAllInBatch();
    }

    @DisplayName("계좌 정보를 저장 후 조회한다.")
    @Test
    void saveTest() {
        Bank savedBank = bankRepository.save(Bank.builder()
                .bankName("kb")
                .imagePath("/test/test.jpg")
                .build());

        AccountServiceRequest accountServiceRequest = AccountServiceRequest.builder()
                .userId(1)
                .bankId(savedBank.getId())
                .accountNumber("1234123412341234")
                .build();

        AccountResponse accountResponse = accountService.save(accountServiceRequest);

        Account account = accountReadService.findById(accountResponse.getId());

        assertAll(
                () -> assertThat(account).extracting("id", "userId", "accountNumber")
                        .contains(accountResponse.getId(), 1, "1234123412341234"),
                () -> assertThat(account.getBank())
                        .extracting("bankName", "imagePath")
                        .containsExactly("kb", "/test/test.jpg")
        );
    }
}
