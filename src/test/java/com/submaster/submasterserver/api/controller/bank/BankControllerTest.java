package com.submaster.submasterserver.api.controller.bank;

import com.submaster.submasterserver.ControllerTestSupport;
import com.submaster.submasterserver.api.controller.bank.request.BankRequest;
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

public class BankControllerTest extends ControllerTestSupport {

    @DisplayName("은행을 저장한다.")
    @Test
    @WithMockUser("USER")
    void saveTest() throws Exception {
        //given
        BankRequest request = BankRequest.builder()
                .bankName("kb")
                .imagePath("/test/test.jpg")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/bank")
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

    @DisplayName("은행을 저장할시 은행명은 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutBankNameTest() throws Exception {
        //given
        BankRequest request = BankRequest.builder()
                .imagePath("/test/test.jpg")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/bank")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("은행명은 필수입니다."));
    }

    @DisplayName("은행을 전체 조회한다.")
    @Test
    @WithMockUser("USER")
    void findAllTest() throws Exception {
        //given
        // when // then
        mockMvc.perform(
                        get("/api/v1/bank")
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("은행명을 입력받아 조회한다.")
    @Test
    @WithMockUser("USER")
    void findByNameContainingTest() throws Exception {
        //given
        // when // then
        mockMvc.perform(
                        get("/api/v1/bank/{bankName}", "kb")
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
