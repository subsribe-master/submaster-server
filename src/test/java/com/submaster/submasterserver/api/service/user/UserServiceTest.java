package com.submaster.submasterserver.api.service.user;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.service.user.request.UserServiceRequest;
import com.submaster.submasterserver.api.service.user.response.UserResponse;
import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import com.submaster.submasterserver.entity.user.User;
import com.submaster.submasterserver.entity.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserReadService userReadService;

    @AfterEach
    void afterEach() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("사용자를 저장한 후 이메일로 사용자를 조회한다.")
    @Test
    void saveTest() {
        UserServiceRequest userServiceRequest = UserServiceRequest.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .snsType(SnsType.KAKAO)
                .build();

        UserResponse userResponse = userService.save(userServiceRequest);

        User user =  userReadService.findById(userResponse.getUserId());

        assertThat(user)
                .extracting("email", "nickName", "snsType", "role", "useYn")
                .containsExactly("test@test.com", "test", SnsType.KAKAO, Role.USER, UseYn.YES);
    }

}
