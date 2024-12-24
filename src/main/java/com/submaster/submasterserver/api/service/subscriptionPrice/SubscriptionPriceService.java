package com.submaster.submasterserver.api.service.subscriptionPrice;

import com.submaster.submasterserver.api.service.subscription.SubscriptionReadService;
import com.submaster.submasterserver.api.service.subscriptionPrice.request.SubscriptionPriceListServiceRequest;
import com.submaster.submasterserver.api.service.subscriptionPrice.request.SubscriptionPriceServiceRequest;
import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionPriceService {

    private final SubscriptionReadService subscriptionReadService;

    public void save(SubscriptionPriceListServiceRequest subscriptionPriceListServiceRequest) {
        Subscription subscription = subscriptionReadService.findById(subscriptionPriceListServiceRequest.getSubscriptionId());

        List<SubscriptionPrice> subscriptionPrices = subscriptionPriceListServiceRequest.getPrice().stream()
                .map(SubscriptionPriceServiceRequest::toEntity)
                .toList();

        subscription.createPrices(subscriptionPrices);
    }

}
