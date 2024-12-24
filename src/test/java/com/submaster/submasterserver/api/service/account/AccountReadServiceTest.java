package com.submaster.submasterserver.api.service.account;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.service.account.reseponse.AccountFindUserResponse;
import com.submaster.submasterserver.entity.account.Account;
import com.submaster.submasterserver.entity.account.AccountRepository;
import com.submaster.submasterserver.entity.account.UseYn;
import com.submaster.submasterserver.entity.bank.Bank;
import com.submaster.submasterserver.entity.bank.BankRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@Transactional
class AccountReadServiceTest extends IntegrationTestSupport {

    @Autowired
    private AccountReadService accountReadService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;

    @AfterEach
    void tearDown() {
        accountRepository.deleteAllInBatch();
    }

    @DisplayName("사용자의 계좌정보들을 조회한다.")
    @Test
    void findByUserIdTest() {
        Bank kbBank = createBank("kb");
        Bank shinhanBank = createBank("shinhan");

        bankRepository.save(kbBank);
        bankRepository.save(shinhanBank);

        accountRepository.save(createAccount(1, kbBank));
        accountRepository.save(createAccount(1, shinhanBank));
        accountRepository.save(createAccount(2, kbBank));

        AccountFindUserResponse accountFindUserResponse = accountReadService.findByUserId(1);

        assertThat(accountFindUserResponse.getAccount())
                .extracting("userId", "bank.bankName", "bank.imagePath", "accountNumber")
                .contains(
                        new Tuple(1, "kb", "/test/test.jpg", "1234123412341234"),
                        new Tuple(1, "shinhan", "/test/test.jpg", "1234123412341234")
                );
    }

    private Account createAccount(int userId, Bank bank) {
        return Account.builder()
                .userId(userId)
                .bank(bank)
                .accountNumber("1234123412341234")
                .useYn(UseYn.YES)
                .build();
    }

    private Bank createBank(String bankName) {
        return Bank.builder()
                .bankName(bankName)
                .imagePath("/test/test.jpg")
                .build();
    }
}
