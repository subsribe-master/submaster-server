package com.submaster.submasterserver.entity.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findByServiceNameContaining(String serviceName);
}
