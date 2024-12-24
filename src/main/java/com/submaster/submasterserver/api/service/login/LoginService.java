package com.submaster.submasterserver.api.service.login;

import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.login.request.LoginServiceRequest;
import com.submaster.submasterserver.api.service.login.response.LoginResponse;
import com.submaster.submasterserver.api.service.user.UserReadService;
import com.submaster.submasterserver.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private static final String NOT_MATCH_PASSWORD_MESSAGE = "비밀번호가 일치하지 않습니다.";

    private final UserReadService userReadService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponse login(LoginServiceRequest loginServiceRequest) {
        User user = userReadService.findByEmail(loginServiceRequest.getEmail());

        if (!bCryptPasswordEncoder.matches(loginServiceRequest.getPassword(), user.getPassword())) {
            throw new SubMasterException(NOT_MATCH_PASSWORD_MESSAGE);
        }

        return LoginResponse.of(user);
    }

}
