package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Account")
public class AccountEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Builder
    public AccountEntity(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Account toDomain() {
        return new Account(id, userId, name);
    }

}
