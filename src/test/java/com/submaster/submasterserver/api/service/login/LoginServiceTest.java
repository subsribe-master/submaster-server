package com.submaster.submasterserver.api.service.login;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.login.request.LoginServiceRequest;
import com.submaster.submasterserver.api.service.login.response.LoginResponse;
import com.submaster.submasterserver.api.service.user.UserService;
import com.submaster.submasterserver.api.service.user.request.UserServiceRequest;
import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import com.submaster.submasterserver.entity.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LoginServiceTest extends IntegrationTestSupport {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("사용자가 존재하면 조회한다.")
    @Test
    void loginTest() {
        UserServiceRequest userServiceRequest = UserServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .snsType(SnsType.NORMAL)
                .build();

        userService.save(userServiceRequest);

        LoginServiceRequest loginServiceRequest = LoginServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .build();

        LoginResponse loginResponse = loginService.login(loginServiceRequest);

        assertThat(loginResponse)
                .extracting("email", "nickName", "snsType", "role", "useYn")
                .containsExactly("test@test.com", "test", SnsType.NORMAL, Role.USER, UseYn.YES);
    }

    @DisplayName("비밀번호가 일치하지 않으면 예외가 발생한다.")
    @Test
    void loginNotMatchPasswordThrowExceptionTest() {
        UserServiceRequest userServiceRequest = UserServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .snsType(SnsType.NORMAL)
                .build();

        userService.save(userServiceRequest);

        LoginServiceRequest loginServiceRequest = LoginServiceRequest
                .builder()
                .email("test@test.com")
                .password("testtest")
                .build();

        assertThatThrownBy(() -> loginService.login(loginServiceRequest))
                .isInstanceOf(SubMasterException.class)
                .hasMessage("비밀번호가 일치하지 않습니다.");
    }

    @DisplayName("사용자가 존재하지 않으면 예외가 발생한다.")
    @Test
    void loginNotExistUserThrowExceptionTest() {
        LoginServiceRequest loginServiceRequest = LoginServiceRequest
                .builder()
                .email("test@test.com")
                .password("test")
                .build();

        assertThatThrownBy(() -> loginService.login(loginServiceRequest))
                .isInstanceOf(SubMasterException.class)
                .hasMessage("해당 사용자는 존재하지 않습니다.");
    }
}
