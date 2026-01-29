package com.dolmengi.common.domain.group;

import com.dolmengi.common.domain.account.Account;

public record ChatGroupUser(Long id, ChatGroup chatGroup, Account account, GroupUserType type) {

}
