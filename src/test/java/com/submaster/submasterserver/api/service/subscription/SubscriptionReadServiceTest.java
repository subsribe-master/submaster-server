package com.submaster.submasterserver.api.service.subscription;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionReadServiceTest extends IntegrationTestSupport {

    @Autowired
    private SubscriptionReadService subscriptionReadService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @AfterEach
    void tearDown() {
        subscriptionRepository.deleteAllInBatch();
    }

    @DisplayName("구독서비스 ID 조회한다.")
    @Test
    void findByIdTest() {
        Subscription subscription = Subscription.builder()
                .serviceName("Neflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        assertThat(subscriptionReadService.findById(savedSubscription.getId()))
                .extracting("serviceName", "imagePath", "subscribeUrl", "cancelUrl")
                .containsExactly("Neflix", "/test/test.png", "www.test.com", "www.test.com");
    }

    @DisplayName("구독서비스 ID 조회시 존재하지 않으면 예외가 발생한다.")
    @Test
    void findByIdNotExistThrowExceptionTest() {
        assertThatThrownBy(() -> subscriptionReadService.findById(100))
                .isInstanceOf(SubMasterException.class)
                .hasMessage("존재하지 않는 구독서비스 입니다.");
    }

    @DisplayName("구독서비스 이름으로 조회한다.")
    @Test
    void findByNameTest() {
        Subscription subscription = Subscription.builder()
                .serviceName("Netflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        subscriptionRepository.save(subscription);

        assertThat(subscriptionReadService.findByName("Netflix").getSubscriptions())
                .extracting("serviceName", "imagePath", "subscribeUrl", "cancelUrl")
                .containsExactly(tuple("Netflix", "/test/test.png", "www.test.com", "www.test.com"));
    }

    @DisplayName("구독서비스 목록을 조회한다.")
    @Test
    void findAllTest() {
        Subscription subscription = Subscription.builder()
                .serviceName("Netflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        subscriptionRepository.save(subscription);

        assertThat(subscriptionReadService.findAll().getSubscriptions())
                .extracting("serviceName", "imagePath", "subscribeUrl", "cancelUrl")
                .containsExactly(tuple("Netflix", "/test/test.png", "www.test.com", "www.test.com"));
    }

}
