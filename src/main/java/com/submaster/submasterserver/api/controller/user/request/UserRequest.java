package com.submaster.submasterserver.api.controller.user.request;

import com.submaster.submasterserver.api.service.user.request.UserServiceRequest;
import com.submaster.submasterserver.entity.user.SnsType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotNull(message = "이메일은 필수입니다.")
    private final String email;
    @NotNull(message = "비밀번호는 필수입니다.")
    private final String password;
    @NotNull(message = "로그인타입은 필수입니다.")
    private final SnsType snsType;
    private final String nickName;

    @Builder
    public UserRequest(String email, String password, String nickName, SnsType snsType) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.snsType = snsType;
    }

    public UserServiceRequest toServiceRequest(){
        return UserServiceRequest.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .snsType(snsType)
                .build();
    }
}
