package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.message.ChatMessage;
import com.dolmengi.common.domain.message.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ChatMessage")
public class ChatMessageEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channelId", nullable = false, updatable = false)
    private ChatChannelEntity chatChannel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupUserId", nullable = false, updatable = false)
    private ChatGroupUserEntity chatGroupUser;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String content;

    private String optional;

    @Builder
    public ChatMessageEntity(ChatChannelEntity chatChannel, ChatGroupUserEntity chatGroupUser, MessageType type, String content, String optional) {
        this.chatChannel = chatChannel;
        this.chatGroupUser = chatGroupUser;
        this.type = type;
        this.content = content;
        this.optional = optional;
    }

    public ChatMessage toDomain() {
        return new ChatMessage(id, chatChannel.toDomain(), chatGroupUser.toDomain(), type, content, optional);
    }

}
