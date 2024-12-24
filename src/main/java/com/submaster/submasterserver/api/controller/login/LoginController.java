package com.submaster.submasterserver.api.controller.login;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.controller.login.request.LoginRequest;
import com.submaster.submasterserver.api.service.login.LoginService;
import com.submaster.submasterserver.api.service.login.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResponse.ok(loginService.login(loginRequest.toServiceRequest()));
    }
}
