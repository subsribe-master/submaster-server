package com.submaster.submasterserver.api.service.user.response;

import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.entity.user.SnsType;
import com.submaster.submasterserver.entity.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final int userId;
    private final String email;
    private final String nickName;
    private final SnsType snsType;
    private final Role role;
    private final UseYn useYn;

    @Builder
    public UserResponse(int userId, String email, String nickName, SnsType snsType, Role role, UseYn useYn) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.snsType = snsType;
        this.role = role;
        this.useYn = useYn;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .snsType(user.getSnsType())
                .role(user.getRole())
                .useYn(user.getUseYn())
                .build();
    }
}
