package com.dolmengi.common.domain.channel;

import com.dolmengi.common.domain.account.Account;

public record ChatChannelUserStatus(ChatChannel chatChannel, Account account, Long lastOffset) {

}
