package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.GroupType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ChatGroup")
public class ChatGroupEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    private Long ownerId;

    private String name;

    @Enumerated(EnumType.STRING)
    private GroupType type;

    private String description;

    private boolean deleted;

    private LocalDateTime deletedAt;

    @Builder
    public ChatGroupEntity(Long ownerId, String name, GroupType type) {
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
    }

    public ChatGroup toDomain() {
        return new ChatGroup(id, ownerId, name, type, description);
    }

    public void changeOwner(ChatGroupUserEntity owner) {
        this.ownerId = owner.toDomain().id();
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

}
