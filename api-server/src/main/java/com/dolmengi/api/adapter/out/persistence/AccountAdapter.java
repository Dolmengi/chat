package com.dolmengi.api.adapter.out.persistence;

import com.dolmengi.api.adapter.out.persistence.entity.AccountEntity;
import com.dolmengi.api.adapter.out.persistence.repository.AccountRepository;
import com.dolmengi.api.application.port.out.persistence.AccountPort;
import com.dolmengi.common.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountAdapter implements AccountPort {

    private final AccountRepository repository;

    @Override
    public Account setAccount(String userId, String name) {
        AccountEntity entity = AccountEntity.builder()
                .userId(userId)
                .name(name)
                .build();

        return repository.save(entity).toDomain();
    }

    @Override
    public Account getAccount(Long id) {
        return repository.findById(id).map(AccountEntity::toDomain).orElse(null);
    }

    @Override
    public Account getAccount(String userId) {
        return repository.findByUserId(userId).map(AccountEntity::toDomain).orElse(null);
    }

}
