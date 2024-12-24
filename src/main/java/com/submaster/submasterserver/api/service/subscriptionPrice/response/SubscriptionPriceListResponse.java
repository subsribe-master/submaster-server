package com.submaster.submasterserver.api.service.subscriptionPrice.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubscriptionPriceListResponse {

    private List<SubscriptionPriceResponse> price;

    @Builder
    public SubscriptionPriceListResponse(List<SubscriptionPriceResponse> price) {
        this.price = price;
    }

    public static SubscriptionPriceListResponse of(List<SubscriptionPriceResponse> price) {
        return SubscriptionPriceListResponse.builder()
                .price(price)
                .build();
    }
}
