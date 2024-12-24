package com.submaster.submasterserver.api.controller.login;

import com.submaster.submasterserver.ControllerTestSupport;
import com.submaster.submasterserver.api.controller.login.request.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest extends ControllerTestSupport {

    @DisplayName("일반 로그인을 한다.")
    @Test
    @WithMockUser("USER")
    void normalLogin() throws Exception {
        // given
        LoginRequest request = LoginRequest.builder()
                .email("tkdrl8908@naver.com")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(
                        get("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("일반 로그인을 할 시 이메일은 필수이다.")
    @Test
    @WithMockUser("USER")
    void normalLoginNotWithoutEmail() throws Exception {
        // given
        LoginRequest request = LoginRequest.builder()
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(
                        get("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("이메일은 필수입니다."));
    }

    @DisplayName("일반 로그인을 할 시 비밀번호는 필수이다.")
    @Test
    @WithMockUser("USER")
    void normalLoginNotWithoutPassword() throws Exception {
        // given
        LoginRequest request = LoginRequest.builder()
                .email("tkdrl8908@naver.com")
                .build();

        // when // then
        mockMvc.perform(
                        get("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("비밀번호는 필수입니다."));
    }

}
