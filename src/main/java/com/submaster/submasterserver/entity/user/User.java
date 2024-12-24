package com.submaster.submasterserver.entity.user;

import com.submaster.submasterserver.entity.BaseTimeEntity;
import com.submaster.submasterserver.entity.UseYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	private String nickName;

	@Enumerated(EnumType.STRING)
	private SnsType snsType;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private UseYn useYn;

	@Builder
	public User(String email, String password, String nickName, SnsType snsType, Role role, UseYn useYn) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.snsType = snsType;
		this.role = role;
		this.useYn = useYn;
	}

	public void updatePassword(String encodePassword) {
		this.password = encodePassword;
	}
}
