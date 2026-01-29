package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.channel.ChannelType;
import com.dolmengi.common.domain.channel.ChatChannel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ChatChannel")
public class ChatChannelEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false, updatable = false)
    private ChatGroupEntity chatGroup;

    private String name;

    @Enumerated(EnumType.STRING)
    private ChannelType type;

    private boolean deleted;

    private LocalDateTime deletedAt;

    @Builder
    public ChatChannelEntity(ChatGroupEntity chatGroup, String name, ChannelType type) {
        this.chatGroup = chatGroup;
        this.name = name;
        this.type = type;
    }

    public ChatChannel toDomain() {
        return new ChatChannel(id, chatGroup.toDomain().id(), name, type);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

}
