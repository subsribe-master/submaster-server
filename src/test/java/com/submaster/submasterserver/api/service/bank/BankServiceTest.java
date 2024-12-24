package com.submaster.submasterserver.api.service.bank;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.service.bank.request.BankServiceRequest;
import com.submaster.submasterserver.api.service.bank.response.BankFindAllResponse;
import com.submaster.submasterserver.api.service.bank.response.BankFindNameResponse;
import com.submaster.submasterserver.entity.bank.Bank;
import com.submaster.submasterserver.entity.bank.BankRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class BankServiceTest extends IntegrationTestSupport {

    @Autowired
    private BankService bankService;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankReadService bankReadService;

    @AfterEach
    void tearDown() {
        bankRepository.deleteAllInBatch();
    }

    @DisplayName("은행을 저장 후 조회한다.")
    @Test
    void saveTest() {
        BankServiceRequest bankServiceRequest = BankServiceRequest.builder()
                .bankName("KB")
                .imagePath("/test/test.jpg")
                .build();

        bankService.save(bankServiceRequest);

        BankFindAllResponse bankFindAllResponse = bankReadService.findAll();

        assertThat(bankFindAllResponse.getBankResponses())
                .extracting("bankName", "imagePath")
                .containsExactly(new Tuple("KB", "/test/test.jpg"));
    }

    @DisplayName("은헹을 저장 후 조회시 일치하는 은행이 없으면 빈배열로 내려준다.")
    @Test
    void findByNameNotTest() {
        bankRepository.save(createBank("KB"));
        bankRepository.save(createBank("Shinhan"));

        BankFindNameResponse bankFindNameResponse = bankReadService.findByBankNameContaining("KB");

        assertThat(bankFindNameResponse.getBankResponses())
                .extracting("bankName", "imagePath")
                .containsExactly(new Tuple("KB", "/test/test.jpg"));
    }

    @DisplayName("은헹을 저장 후 조회시 일치하는 은행이 없으면 빈배열로 내려준다.")
    @Test
    void findByNameNotCorrectTest() {
        bankRepository.save(createBank("KB"));
        bankRepository.save(createBank("Shinhan"));

        BankFindNameResponse bankFindNameResponse = bankReadService.findByBankNameContaining("woori");

        assertThat(bankFindNameResponse.getBankResponses()).isEmpty();
    }

    private Bank createBank(String bankName) {
        return Bank.builder()
                .bankName(bankName)
                .imagePath("/test/test.jpg")
                .build();
    }
}
