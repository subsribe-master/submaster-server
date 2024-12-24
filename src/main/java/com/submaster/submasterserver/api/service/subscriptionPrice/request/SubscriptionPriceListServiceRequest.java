package com.submaster.submasterserver.api.service.subscriptionPrice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubscriptionPriceListServiceRequest {

    private int subscriptionId;
    private List<SubscriptionPriceServiceRequest> price;

    @Builder
    public SubscriptionPriceListServiceRequest(int subscriptionId, List<SubscriptionPriceServiceRequest> price) {
        this.subscriptionId = subscriptionId;
        this.price = price;
    }
}
