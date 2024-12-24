package com.submaster.submasterserver.entity.userAlert;

import com.submaster.submasterserver.entity.BaseTimeEntity;
import com.submaster.submasterserver.entity.UseYn;
import com.submaster.submasterserver.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_alert")
public class UserAlert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private UseYn email;

    @Enumerated(EnumType.STRING)
    private UseYn message;

    @Enumerated(EnumType.STRING)
    private UseYn useYn;

}
