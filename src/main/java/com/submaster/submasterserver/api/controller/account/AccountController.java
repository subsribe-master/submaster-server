package com.submaster.submasterserver.api.controller.account;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.controller.account.request.AccountRequest;
import com.submaster.submasterserver.api.service.account.AccountReadService;
import com.submaster.submasterserver.api.service.account.AccountService;
import com.submaster.submasterserver.api.service.account.reseponse.AccountFindUserResponse;
import com.submaster.submasterserver.api.service.account.reseponse.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountReadService accountReadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<AccountResponse> save(@RequestBody @Valid AccountRequest accountRequest) {
        return ApiResponse.created(accountService.save(accountRequest.toServiceRequest()));
    }

    @GetMapping("/{userId}")
    public ApiResponse<AccountFindUserResponse> findByUserId(@PathVariable int userId) {
        return ApiResponse.ok(accountReadService.findByUserId(userId));
    }
}
