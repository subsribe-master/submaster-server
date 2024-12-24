package com.submaster.submasterserver.api.service.subscription.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubscriptionListResponse {

    private List<SubscriptionResponse> subscriptions;

    @Builder
    public SubscriptionListResponse(List<SubscriptionResponse> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public static SubscriptionListResponse of(List<SubscriptionResponse> subscriptions) {
        return SubscriptionListResponse.builder()
                .subscriptions(subscriptions)
                .build();
    }
}
