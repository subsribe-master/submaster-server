package com.submaster.submasterserver.api.controller.user;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.controller.user.request.UserRequest;
import com.submaster.submasterserver.api.service.user.UserService;
import com.submaster.submasterserver.api.service.user.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/welcome", produces = "text/plain")
    public String welcome() {
        return "welcome user-service";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<UserResponse> save(@RequestBody @Valid UserRequest userRequest) {
        return ApiResponse.created(userService.save(userRequest.toServiceRequest()));
    }

}
