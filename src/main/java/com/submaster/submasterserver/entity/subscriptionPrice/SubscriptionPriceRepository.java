package com.submaster.submasterserver.entity.subscriptionPrice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionPriceRepository extends JpaRepository<SubscriptionPrice, Integer> {

    List<SubscriptionPrice> findAllBySubscriptionId(int subscriptionId);
}
