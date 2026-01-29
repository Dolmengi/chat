package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.adapter.out.persistence.entity.pk.ChatChannelUserId;
import com.dolmengi.common.domain.channel.ChatChannelUserStatus;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ChatChannelUserStatus")
public class ChatChannelUserStatusEntity {

    @EmbeddedId
    private ChatChannelUserId id;

    @ManyToOne
    @JoinColumn(name = "channelId", nullable = false, insertable = false, updatable = false)
    private ChatChannelEntity chatChannel;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false, insertable = false, updatable = false)
    private AccountEntity account;

    private Long lastOffset;

    private LocalDateTime updatedAt;

    @Builder
    public ChatChannelUserStatusEntity(ChatChannelEntity chatChannel, AccountEntity account, Long lastOffset) {
        this.chatChannel = chatChannel;
        this.account = account;
        this.lastOffset = lastOffset;
        this.updatedAt = LocalDateTime.now();
    }

    public ChatChannelUserStatus toDomain() {
        return new ChatChannelUserStatus(chatChannel.toDomain(), account.toDomain(), lastOffset);
    }

}
