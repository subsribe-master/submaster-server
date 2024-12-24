package com.submaster.submasterserver.api.service.subscription;

import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionListResponse;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionResponse;
import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionReadService {

    private static final String NOT_EXIST_SUBSCRIPTION = "존재하지 않는 구독서비스 입니다.";

    private final SubscriptionRepository subscriptionRepository;

    public Subscription findById(int id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubMasterException(NOT_EXIST_SUBSCRIPTION));
    }

    public SubscriptionListResponse findByName(String name) {
        return SubscriptionListResponse.of(
                subscriptionRepository.findByServiceNameContaining(name).stream()
                        .map(SubscriptionResponse::of)
                        .collect(Collectors.toList())
        );
    }

    public SubscriptionListResponse findAll() {
        return SubscriptionListResponse.of(
                subscriptionRepository.findAll().stream()
                        .map(SubscriptionResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
