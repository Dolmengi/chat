package com.dolmengi.api.application.service.user;

import static com.dolmengi.common.constants.UserRole.USER;

import com.dolmengi.api.adapter.in.rest.user.dto.LoginRequest;
import com.dolmengi.api.application.port.in.user.LoginUseCase;
import com.dolmengi.api.application.port.out.persistence.AccountPort;
import com.dolmengi.api.commons.util.JwtUtils;
import com.dolmengi.common.domain.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService implements LoginUseCase {

    private final AccountPort accountPort;

    @Transactional
    @Override
    public String login(LoginRequest request) {
        Account account = accountPort.getAccount(request.userId());
        if (ObjectUtils.isEmpty(account)) {
            account = accountPort.setAccount(request.userId(), request.name());
        }

        return JwtUtils.createToken(account, USER);
    }

}
