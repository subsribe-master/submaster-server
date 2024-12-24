package com.submaster.submasterserver.api.service.subscription.request;

import com.submaster.submasterserver.entity.subscription.Subscription;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionServiceRequest {

    private String serviceName;
    private String imagePath;
    private String subscribeUrl;
    private String cancelUrl;

    @Builder
    public SubscriptionServiceRequest(String serviceName, String imagePath, String subscribeUrl, String cancelUrl) {
        this.serviceName = serviceName;
        this.imagePath = imagePath;
        this.subscribeUrl = subscribeUrl;
        this.cancelUrl = cancelUrl;
    }

    public Subscription toEntity() {
        return Subscription.builder()
                .serviceName(serviceName)
                .imagePath(imagePath)
                .subscribeUrl(subscribeUrl)
                .cancelUrl(cancelUrl)
                .build();
    }

}
