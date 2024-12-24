package com.submaster.submasterserver.api.controller.subcription;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.service.subscription.SubscriptionReadService;
import com.submaster.submasterserver.api.service.subscription.SubscriptionService;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionListResponse;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionReadService subscriptionReadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<SubscriptionResponse> save(@RequestBody @Valid SubscriptionRequest subscriptionRequest) {
        return ApiResponse.created(subscriptionService.save(subscriptionRequest.toServiceRequest()));
    }

    @GetMapping
    public ApiResponse<SubscriptionListResponse> findAll() {
        return ApiResponse.ok(subscriptionReadService.findAll());
    }

    @GetMapping("/{serviceName}")
    public ApiResponse<SubscriptionListResponse> findByName(@PathVariable String serviceName) {
        return ApiResponse.ok(subscriptionReadService.findByName(serviceName));
    }

}
