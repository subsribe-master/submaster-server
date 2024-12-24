package com.submaster.submasterserver.api.service.user.request;

import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import com.submaster.submasterserver.entity.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserServiceRequest {

    private final String email;
    private final String password;
    private final String nickName;
    private final SnsType snsType;

    @Builder
    public UserServiceRequest(String email, String password, String nickName, SnsType snsType) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.snsType = snsType;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .snsType(snsType)
                .role(Role.USER)
                .useYn(UseYn.YES)
                .build();
    }
}
