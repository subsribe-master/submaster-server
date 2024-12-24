package com.submaster.submasterserver.api.controller.account;

import com.submaster.submasterserver.ControllerTestSupport;
import com.submaster.submasterserver.api.controller.account.request.AccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest extends ControllerTestSupport {

    @DisplayName("계좌정보 저장한다.")
    @Test
    @WithMockUser("USER")
    void saveTest() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
                .userId(1)
                .bankId(1)
                .accountNumber("1234123412341234")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.httpStatus").value("CREATED"))
                .andExpect(jsonPath("$.message").value("CREATED"));
    }

    @DisplayName("계좌정보 저장시 사용자 정보는 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutUserIdTest() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
                .bankId(1)
                .accountNumber("1234123412341234")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("사용자 정보는 필수입니다."));
    }

    @DisplayName("계좌정보 저장시 은행 정보는 필수이다..")
    @Test
    @WithMockUser("USER")
    void saveWithoutBankIdTest() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
                .userId(1)
                .accountNumber("1234123412341234")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("은행 정보는 필수입니다."));
    }

    @DisplayName("사용자의 계좌정보들을 조회한다.")
    @Test
    @WithMockUser("USER")
    void findByUserIdTest() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                        get("/api/v1/account/{userId}", 1)
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

}
