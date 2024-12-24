package com.submaster.submasterserver.api.service.subscription;

import com.submaster.submasterserver.IntegrationTestSupport;
import com.submaster.submasterserver.api.service.subscription.request.SubscriptionServiceRequest;
import com.submaster.submasterserver.api.service.subscription.response.SubscriptionResponse;
import com.submaster.submasterserver.entity.subscription.SubscriptionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class SubscriptionServiceTest extends IntegrationTestSupport {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @AfterEach
    void tearDown() {
        subscriptionRepository.deleteAllInBatch();
    }

    @DisplayName("구독서비스를 등록한다.")
    @Test
    void saveTest() {
        SubscriptionServiceRequest subscriptionServiceRequest = SubscriptionServiceRequest.builder()
                .serviceName("Neflix")
                .imagePath("/test/test.png")
                .subscribeUrl("www.test.com")
                .cancelUrl("www.test.com")
                .build();

        SubscriptionResponse subscriptionResponse = subscriptionService.save(subscriptionServiceRequest);

        assertThat(subscriptionRepository.findById(subscriptionResponse.getId()).get())
                .extracting("serviceName", "imagePath", "subscribeUrl", "cancelUrl")
                .containsExactly("Neflix", "/test/test.png", "www.test.com", "www.test.com");
    }

}
