package com.submaster.submasterserver.api.service.subscriptionPrice.request;

import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionPriceServiceRequest {

    private String gradeName;
    private String price;
    private int sort;

    @Builder
    public SubscriptionPriceServiceRequest(String gradeName, String price, int sort) {
        this.gradeName = gradeName;
        this.price = price;
        this.sort = sort;
    }

    public SubscriptionPrice toEntity() {
        return SubscriptionPrice.builder()
                .gradeName(gradeName)
                .price(price)
                .sort(sort)
                .build();
    }
}
