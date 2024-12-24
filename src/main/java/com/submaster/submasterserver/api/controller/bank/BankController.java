package com.submaster.submasterserver.api.controller.bank;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.controller.bank.request.BankRequest;
import com.submaster.submasterserver.api.service.bank.BankReadService;
import com.submaster.submasterserver.api.service.bank.BankService;
import com.submaster.submasterserver.api.service.bank.response.BankFindAllResponse;
import com.submaster.submasterserver.api.service.bank.response.BankFindNameResponse;
import com.submaster.submasterserver.api.service.bank.response.BankResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank")
public class BankController {

    private final BankService bankService;
    private final BankReadService bankReadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<BankResponse> save(@RequestBody @Valid BankRequest bankRequest) {
        return ApiResponse.created(bankService.save(bankRequest.toServiceRequest()));
    }

    @GetMapping
    public ApiResponse<BankFindAllResponse> findAll() {
        return ApiResponse.ok(bankReadService.findAll());
    }

    @GetMapping("/{bankName}")
    public ApiResponse<BankFindNameResponse> findByBankNameContaining(@PathVariable String bankName) {
        return ApiResponse.ok(bankReadService.findByBankNameContaining(bankName));
    }
}
