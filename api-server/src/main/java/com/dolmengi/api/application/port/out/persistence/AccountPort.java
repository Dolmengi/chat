package com.dolmengi.api.application.port.out.persistence;

import com.dolmengi.common.domain.account.Account;

public interface AccountPort {

    Account setAccount(String userId, String name);

    Account getAccount(Long id);

    Account getAccount(String userId);

}
