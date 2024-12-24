package com.submaster.submasterserver.api.service.user;

import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.entity.user.User;
import com.submaster.submasterserver.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReadService {
    private static final String UNKNOWN_USER_MESSAGE = "해당 사용자는 존재하지 않습니다.";

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new SubMasterException(UNKNOWN_USER_MESSAGE));
    }

    public User findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new SubMasterException(UNKNOWN_USER_MESSAGE));
    }
}
