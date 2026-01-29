package com.dolmengi.common.domain.security;

import com.dolmengi.common.constants.UserRole;
import com.dolmengi.common.domain.account.Account;
import java.io.Serializable;

public record SessionContext(Account account, UserRole role) implements Serializable {


}
