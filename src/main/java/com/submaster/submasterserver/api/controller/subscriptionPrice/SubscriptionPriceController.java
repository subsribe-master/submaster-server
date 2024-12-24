package com.submaster.submasterserver.api.controller.subscriptionPrice;

import com.submaster.submasterserver.api.ApiResponse;
import com.submaster.submasterserver.api.service.subscriptionPrice.SubscriptionPriceReadService;
import com.submaster.submasterserver.api.service.subscriptionPrice.response.SubscriptionPriceListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptionPrice")
public class SubscriptionPriceController {

    private final SubscriptionPriceReadService subscriptionPriceReadService;

    @GetMapping("/{subscriptionId}")
    public ApiResponse<SubscriptionPriceListResponse> findBySubscriptionId(@PathVariable int subscriptionId) {
        return ApiResponse.ok(subscriptionPriceReadService.findBySubscriptionId(subscriptionId));
    }
}
