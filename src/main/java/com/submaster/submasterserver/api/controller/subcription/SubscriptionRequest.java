package com.submaster.submasterserver.api.controller.subcription;

import com.submaster.submasterserver.api.service.subscription.request.SubscriptionServiceRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionRequest {

    @NotNull(message = "서비스명은 필수입니다.")
    private String serviceName;
    private String imagePath;
    private String subscribeUrl;
    private String cancelUrl;

    @Builder
    public SubscriptionRequest(String serviceName, String imagePath, String subscribeUrl, String cancelUrl) {
        this.serviceName = serviceName;
        this.imagePath = imagePath;
        this.subscribeUrl = subscribeUrl;
        this.cancelUrl = cancelUrl;
    }

    public SubscriptionServiceRequest toServiceRequest() {
        return SubscriptionServiceRequest.builder()
                .serviceName(serviceName)
                .imagePath(imagePath)
                .subscribeUrl(subscribeUrl)
                .cancelUrl(cancelUrl)
                .build();
    }
}
