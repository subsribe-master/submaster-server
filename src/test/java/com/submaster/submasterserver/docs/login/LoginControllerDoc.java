package com.submaster.submasterserver.docs.login;

import com.submaster.submasterserver.RestDocsSupport;
import com.submaster.submasterserver.api.controller.login.LoginController;
import com.submaster.submasterserver.api.controller.login.request.LoginRequest;
import com.submaster.submasterserver.api.service.login.LoginService;
import com.submaster.submasterserver.api.service.login.request.LoginServiceRequest;
import com.submaster.submasterserver.api.service.login.response.LoginResponse;
import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerDoc extends RestDocsSupport {
    private final LoginService loginService = mock(LoginService.class);

    @Override
    protected Object initController() {
        return new LoginController(loginService);
    }

    @DisplayName("일반 로그인 API")
    @Test
    void normalLogin() throws Exception {
        // given
        LoginRequest request = LoginRequest.builder()
                .email("test@test.com")
                .password("1234")
                .build();

        given(loginService.login(any(LoginServiceRequest.class)))
                .willReturn(LoginResponse.builder()
                        .userId(1)
                        .email("test@test.com")
                        .nickName("test")
                        .snsType(SnsType.NORMAL)
                        .role(Role.USER)
                        .useYn(UseYn.YES)
                        .build()
                );

        // when // then
        mockMvc.perform(
                        get("/api/v1/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호")
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
                                fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                                        .description("사용자 key"),
                                fieldWithPath("data.email").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                fieldWithPath("data.nickName").type(JsonFieldType.STRING)
                                        .description("닉네임"),
                                fieldWithPath("data.snsType").type(JsonFieldType.STRING)
                                        .description("로그인타입. 가능한 값: " + Arrays.toString(SnsType.values())),
                                fieldWithPath("data.role").type(JsonFieldType.STRING)
                                        .description("권한. 가능한 값: " + Arrays.toString(Role.values())),
                                fieldWithPath("data.useYn").type(JsonFieldType.STRING)
                                        .description("사용여부. 가능한 값: " + Arrays.toString(UseYn.values()))
                        )
                ));
    }
}
