package com.submaster.submasterserver.docs.account;

import com.submaster.submasterserver.RestDocsSupport;
import com.submaster.submasterserver.api.controller.account.AccountController;
import com.submaster.submasterserver.api.controller.account.request.AccountRequest;
import com.submaster.submasterserver.api.service.account.AccountReadService;
import com.submaster.submasterserver.api.service.account.AccountService;
import com.submaster.submasterserver.api.service.account.request.AccountServiceRequest;
import com.submaster.submasterserver.api.service.account.reseponse.AccountFindUserResponse;
import com.submaster.submasterserver.api.service.account.reseponse.AccountResponse;
import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerDocsTest extends RestDocsSupport {

    private final AccountReadService accountReadService = mock(AccountReadService.class);
    private final AccountService accountService = mock(AccountService.class);

    @Override
    protected Object initController() {
        return new AccountController(accountService, accountReadService);
    }

    @DisplayName("은행 저장 API")
    @Test
    void save() throws Exception {
        // given
        AccountRequest request = AccountRequest.builder()
                .userId(1)
                .bankId(1)
                .accountNumber("1234123412341234")
                .build();

        given(accountService.save(any(AccountServiceRequest.class)))
                .willReturn(AccountResponse.builder()
                        .id(1)
                        .userId(1)
                        .bank(BankResponse.builder()
                                .id(1)
                                .bankName("kb")
                                .imagePath("/test/test.jpg")
                                .build())
                        .accountNumber("1234123412341234")
                        .build()
                );

        // when // then
        mockMvc.perform(
                        post("/api/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("account-save",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER)
                                        .description("사용자ID"),
                                fieldWithPath("bankId").type(JsonFieldType.NUMBER)
                                        .description("은행ID"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호")
                        ),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("httpStatus").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메세지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("계좌 key"),
                                fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                                        .description("사용자ID"),
                                fieldWithPath("data.bank").type(JsonFieldType.OBJECT)
                                        .description("은행정보"),
                                fieldWithPath("data.bank.id").type(JsonFieldType.NUMBER)
                                        .description("은행 key"),
                                fieldWithPath("data.bank.bankName").type(JsonFieldType.STRING)
                                        .description("은행 이름"),
                                fieldWithPath("data.bank.imagePath").type(JsonFieldType.STRING)
                                        .description("은행 이미지"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호")
                        )
                ));
    }

    @DisplayName("사용자 계좌정보 리스트조회 API")
    @Test
    void findByUserIdTest() throws Exception {
        // given
        given(accountReadService.findByUserId(any(Integer.class)))
                .willReturn(AccountFindUserResponse.builder()
                        .account(List.of(
                                AccountResponse.builder()
                                        .id(1)
                                        .userId(1)
                                        .bank(BankResponse.builder()
                                                .id(1)
                                                .bankName("kb")
                                                .imagePath("/test/test.jpg")
                                                .build())
                                        .accountNumber("1234123412341234")
                                        .build(),
                                AccountResponse.builder()
                                        .id(1)
                                        .userId(1)
                                        .bank(BankResponse.builder()
                                                .id(1)
                                                .bankName("shinhan")
                                                .imagePath("/test/test.jpg")
                                                .build())
                                        .accountNumber("1234123412341234")
                                        .build()
                        ))
                        .build()
                );

        // when // then
        mockMvc.perform(
                        get("/api/v1/account/{userId}", 1)
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("account-findUser",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("httpStatus").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메세지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.account[]").type(JsonFieldType.ARRAY)
                                        .description("계좌정보 Array"),
                                fieldWithPath("data.account[].userId").type(JsonFieldType.NUMBER)
                                        .description("사용자ID"),
                                fieldWithPath("data.account[].id").type(JsonFieldType.NUMBER)
                                        .description("계좌 key"),
                                fieldWithPath("data.account[].bank").type(JsonFieldType.OBJECT)
                                        .description("은행정보"),
                                fieldWithPath("data.account[].bank.id").type(JsonFieldType.NUMBER)
                                        .description("은행 key"),
                                fieldWithPath("data.account[].bank.bankName").type(JsonFieldType.STRING)
                                        .description("은행 이름"),
                                fieldWithPath("data.account[].bank.imagePath").type(JsonFieldType.STRING)
                                        .description("은행 이미지"),
                                fieldWithPath("data.account[].accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호")
                        )
                ));
    }
}
