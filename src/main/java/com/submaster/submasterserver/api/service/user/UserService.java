package com.submaster.submasterserver.api.service.user;

import com.submaster.submasterserver.api.service.user.request.UserServiceRequest;
import com.submaster.submasterserver.api.service.user.response.UserResponse;
import com.submaster.submasterserver.entity.user.User;
import com.submaster.submasterserver.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse save(UserServiceRequest userServiceRequest) {
        User user = userServiceRequest.toEntity();
        user.updatePassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return UserResponse.of(userRepository.save(user));
    }

}
