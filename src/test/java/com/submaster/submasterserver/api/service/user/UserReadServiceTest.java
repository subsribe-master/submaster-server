package com.submaster.submasterserver.api.service.user;

import com.submaster.submasterserver.IntegrationTestSupport;
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

class UserReadServiceTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserReadService userReadService;

    @AfterEach
    void afterEach() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("사용자를 저장한 후 사용자를 조회한다.")
    @Test
    void saveTest() {
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .nickName("test")
                .snsType(SnsType.KAKAO)
                .role(Role.USER)
                .useYn(UseYn.YES)
                .build();

        User savedUser = userRepository.save(user);

        assertThat(userReadService.findByEmail(savedUser.getEmail()))
                .extracting("email", "nickName", "snsType", "role", "useYn")
                .containsExactly("test@test.com", "test", SnsType.KAKAO, Role.USER, UseYn.YES);
    }

}
