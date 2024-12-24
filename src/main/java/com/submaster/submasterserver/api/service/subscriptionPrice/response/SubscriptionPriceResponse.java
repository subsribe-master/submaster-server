package com.submaster.submasterserver.api.service.subscriptionPrice.response;

import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionPriceResponse {
    private int id;
    private String gradeName;
    private String price;
    private int sort;

    @Builder
    public SubscriptionPriceResponse(int id, String gradeName, String price, int sort) {
        this.id = id;
        this.gradeName = gradeName;
        this.price = price;
        this.sort = sort;
    }

    public static SubscriptionPriceResponse of(SubscriptionPrice subscriptionPrice) {
        return SubscriptionPriceResponse.builder()
                .id(subscriptionPrice.getId())
                .gradeName(subscriptionPrice.getGradeName())
                .price(subscriptionPrice.getPrice())
                .sort(subscriptionPrice.getSort())
                .build();
    }
}
