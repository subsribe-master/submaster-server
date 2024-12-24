package com.submaster.submasterserver.entity.subscription;

import com.submaster.submasterserver.entity.BaseTimeEntity;
import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.subscriptionPrice.SubscriptionPrice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscription extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubscriptionPrice> prices = new ArrayList<>();

    private String serviceName;

    private String imagePath;

    private String subscribeUrl;

    private String cancelUrl;

    private UseYn useYn;

    @Builder
    public Subscription(String serviceName, String imagePath, String subscribeUrl, String cancelUrl) {
        this.serviceName = serviceName;
        this.imagePath = imagePath;
        this.subscribeUrl = subscribeUrl;
        this.cancelUrl = cancelUrl;
        this.useYn = UseYn.YES;
    }

    public void createPrices(List<SubscriptionPrice> prices) {
        this.prices = prices;
        prices.forEach(subscriptionPrice -> subscriptionPrice.createSubscription(this));
    }
}
