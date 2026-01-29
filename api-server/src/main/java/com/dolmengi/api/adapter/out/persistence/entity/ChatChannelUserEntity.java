package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.channel.ChatChannelUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ChatChannelUser")
public class ChatChannelUserEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channelId", nullable = false, updatable = false)
    private ChatChannelEntity chatChannel;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false, updatable = false)
    private AccountEntity account;

    private LocalDateTime leftAt;

    @Builder
    public ChatChannelUserEntity(ChatChannelEntity chatChannel, AccountEntity account) {
        this.chatChannel = chatChannel;
        this.account = account;
    }

    public ChatChannelUser toDomain() {
        return new ChatChannelUser(id, chatChannel.toDomain(), account.toDomain());
    }

}
