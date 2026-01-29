package com.dolmengi.common.domain.channel;

import com.dolmengi.common.domain.account.Account;

public record ChatChannelUser(Long id, ChatChannel chatChannel, Account account) {

}
