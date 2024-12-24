package com.submaster.submasterserver.api.service.subscriptionPrice;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.groups.Tuple.tuple;
import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionPriceReadServiceTest extends IntegrationTestSupport {

    @Autowired
    private SubscriptionPriceReadService subscriptionPriceReadService;
    @Autowired
    private SubscriptionPriceRepository subscriptionPriceRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @DisplayName("구독서비스의 가격을 조회한다.")
    @Test
    void findBySubscriptionIdTest() {
        Subscription subscription = Subscription.builder()
                .serviceName("Neflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        SubscriptionPrice subscriptionPrice = SubscriptionPrice.builder()
                .subscription(savedSubscription)
                .gradeName("스탠다드")
                .price("10000")
                .sort(1)
                .build();

        subscriptionPriceRepository.save(subscriptionPrice);

        assertThat(subscriptionPriceReadService.findBySubscriptionId(savedSubscription.getId()).getPrice())
                .extracting("gradeName", "price", "sort")
                .containsExactly(tuple("스탠다드", "10000", 1));
    }

    @DisplayName("구독서비스의 가격을 조회할시 존재하지 않으면 빈배열을 반환한다.")
    @Test
    void findBySubscriptionIdNotExistThrowExceptionTest() {
        assertThat(subscriptionPriceReadService.findBySubscriptionId(100).getPrice()).hasSize(0);
    }
}
