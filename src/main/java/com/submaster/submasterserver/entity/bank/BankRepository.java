package com.submaster.submasterserver.entity.bank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    List<Bank> findByBankNameContaining(String bankName);
}
