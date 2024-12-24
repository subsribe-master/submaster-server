package com.submaster.submasterserver.api.service.subscriptionPrice;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.api.service.subscriptionPrice.request.SubscriptionPriceListServiceRequest;
import com.submaster.submasterserver.api.service.subscriptionPrice.request.SubscriptionPriceServiceRequest;
import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@Transactional
class SubscriptionPriceServiceTest extends IntegrationTestSupport {

    @Autowired
    private SubscriptionPriceService subscriptionPriceService;
    @Autowired
    private SubscriptionPriceRepository subscriptionPriceRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @AfterEach
    void tearDown() {
        subscriptionPriceRepository.deleteAllInBatch();
        subscriptionRepository.deleteAllInBatch();
    }

    @DisplayName("구독서비스를 가격을 저장한다.")
    @Test
    void saveTest() {
        Subscription subscription = Subscription.builder()
                .serviceName("Neflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        List<SubscriptionPriceServiceRequest> subscriptionPriceRequest = List.of(
                SubscriptionPriceServiceRequest.builder()
                        .gradeName("스탠다드")
                        .price("10000")
                        .sort(1)
                        .build()
        );

        SubscriptionPriceListServiceRequest subscriptionPriceListServiceRequest = SubscriptionPriceListServiceRequest.builder()
                .subscriptionId(savedSubscription.getId())
                .price(subscriptionPriceRequest)
                .build();

        subscriptionPriceService.save(subscriptionPriceListServiceRequest);

        List<SubscriptionPrice> subscriptionPrices = subscriptionPriceRepository.findAllBySubscriptionId(savedSubscription.getId());

        assertThat(subscriptionPrices).extracting("gradeName","price","sort")
                .containsExactly(tuple("스탠다드", "10000", 1));
    }

    @DisplayName("구독서비스를 가격을 저장시 구독서비스가 존재하지 않으면 예외가 발생한다.")
    @Test
    void saveNotExistThrowExceptionTest() {
        List<SubscriptionPriceServiceRequest> subscriptionPriceRequest = List.of(
                SubscriptionPriceServiceRequest.builder()
                        .gradeName("스탠다드")
                        .price("10000")
                        .sort(1)
                        .build()
        );

        SubscriptionPriceListServiceRequest subscriptionPriceListServiceRequest = SubscriptionPriceListServiceRequest.builder()
                .subscriptionId(100)
                .price(subscriptionPriceRequest)
                .build();

        assertThatThrownBy(() -> subscriptionPriceService.save(subscriptionPriceListServiceRequest))
                .isInstanceOf(SubMasterException.class)
                .hasMessage("존재하지 않는 구독서비스 입니다.");
    }

}
