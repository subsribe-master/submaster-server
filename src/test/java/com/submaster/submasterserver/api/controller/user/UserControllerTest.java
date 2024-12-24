package com.submaster.submasterserver.api.controller.user;

import com.submaster.submasterserver.ControllerTestSupport;
import com.submaster.submasterserver.api.controller.user.request.UserRequest;
import com.submaster.submasterserver.entity.user.SnsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTestSupport {

    @DisplayName("사용자를 저장한다.")
    @Test
    @WithMockUser("USER")
    void saveTest() throws Exception {
        // given
        UserRequest request = UserRequest.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .snsType(SnsType.KAKAO)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/user")
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

    @DisplayName("사용자를 저장시 이메일은 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutEmailTest() throws Exception {
        // given
        UserRequest request = UserRequest.builder()
                .password("test")
                .nickName("test")
                .snsType(SnsType.KAKAO)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/user")
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

    @DisplayName("사용자를 저장할 시 패드워드는 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutPasswordTest() throws Exception {
        // given
        UserRequest request = UserRequest.builder()
                .email("test@test.com")
                .nickName("test")
                .snsType(SnsType.KAKAO)
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/user")
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

    @DisplayName("사용자를 저장할시 로그인타입은 필수이다.")
    @Test
    @WithMockUser("USER")
    void saveWithoutSnsTypeTest() throws Exception {
        // given
        UserRequest request = UserRequest.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .build();

        // when // then
        mockMvc.perform(
                        post("/api/v1/user")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("로그인타입은 필수입니다."));
    }

}
