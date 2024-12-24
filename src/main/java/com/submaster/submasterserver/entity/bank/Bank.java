package com.submaster.submasterserver.entity.bank;

import com.submaster.submasterserver.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankName;

    private String imagePath;

    @Builder
    public Bank(int id, String bankName, String imagePath) {
        this.id = id;
        this.bankName = bankName;
        this.imagePath = imagePath;
    }
}
