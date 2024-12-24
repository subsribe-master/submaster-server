package com.submaster.submasterserver.api.service.subscription;

import com.submaster.submasterserver.api.service.subscription.request.SubscriptionServiceRequest;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionResponse;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionResponse save(SubscriptionServiceRequest subscriptionServiceRequest) {
        return SubscriptionResponse.of(subscriptionRepository.save(subscriptionServiceRequest.toEntity()));
    }
}
