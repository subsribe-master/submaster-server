package com.submaster.submasterserver.api.service.subscriptionPrice;

import com.submaster.submasterserver.api.service.subscriptionPrice.response.SubscriptionPriceListResponse;
import com.submaster.submasterserver.api.service.subscriptionPrice.response.SubscriptionPriceResponse;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionPriceReadService {

    private final SubscriptionPriceRepository subscriptionPriceRepository;

    public SubscriptionPriceListResponse findBySubscriptionId(int subscriptionId) {
        return SubscriptionPriceListResponse.of(
                subscriptionPriceRepository.findAllBySubscriptionId(subscriptionId).stream()
                        .map(SubscriptionPriceResponse::of)
                        .toList()
        );
    }
}
