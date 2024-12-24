package com.submaster.submasterserver.api.service.subscription.response;

import com.submaster.submasterserver.entity.subscription.Subscription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionResponse {

    private int id;
    private String serviceName;
    private String imagePath;
    private String subscribeUrl;
    private String cancelUrl;

    @Builder
    public SubscriptionResponse(int id, String serviceName, String imagePath, String subscribeUrl, String cancelUrl) {
        this.id = id;
        this.serviceName = serviceName;
        this.imagePath = imagePath;
        this.subscribeUrl = subscribeUrl;
        this.cancelUrl = cancelUrl;
    }

    public static SubscriptionResponse of(Subscription subscription) {
        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .serviceName(subscription.getServiceName())
                .imagePath(subscription.getImagePath())
                .subscribeUrl(subscription.getSubscribeUrl())
                .cancelUrl(subscription.getCancelUrl())
                .build();
    }
}
