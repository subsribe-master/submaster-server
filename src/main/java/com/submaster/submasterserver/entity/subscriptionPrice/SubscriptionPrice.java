package com.submaster.submasterserver.entity.subscriptionPrice;

import com.submaster.submasterserver.entity.subscription.Subscription;
import com.submaster.submasterserver.entity.UseYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subscription subscription;

    private String gradeName;

    private String price;

    private int sort;

    private UseYn useYn;

    @Builder
    public SubscriptionPrice(Subscription subscription, String gradeName, String price, int sort, UseYn useYn) {
        this.subscription = subscription;
        this.gradeName = gradeName;
        this.price = price;
        this.sort = sort;
        this.useYn = useYn;
    }

    public void createSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
