package com.submaster.submasterserver.api.service.login.response;

import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import com.submaster.submasterserver.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private int userId;
    private String email;
    private String nickName;
    private SnsType snsType;
    private Role role;
    private UseYn useYn;

    @Builder
    private LoginResponse(int userId, String email, String nickName, SnsType snsType, Role role, UseYn useYn) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.snsType = snsType;
        this.role = role;
        this.useYn = useYn;
    }

    public static LoginResponse of(User user) {
        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .snsType(user.getSnsType())
                .role(user.getRole())
                .useYn(user.getUseYn())
                .build();
    }
}
