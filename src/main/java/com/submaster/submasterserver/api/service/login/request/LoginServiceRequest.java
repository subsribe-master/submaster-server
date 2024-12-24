package com.submaster.submasterserver.api.service.login.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginServiceRequest {

    private final String email;
    private final String password;

    @Builder
    public LoginServiceRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
