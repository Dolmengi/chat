package com.dolmengi.api.adapter.out.persistence.entity;

import com.dolmengi.api.commons.jpa.Tsid;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupUserType;
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
@Entity(name = "ChatGroupUser")
public class ChatGroupUserEntity extends BaseEntity {

    @Id
    @Tsid
    private Long id;

    //@MapsId("groupId")
    @ManyToOne
    @JoinColumn(name = "groupId", nullable = false, updatable = false)
    private ChatGroupEntity chatGroup;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false, updatable = false)
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    private GroupUserType type;

    private LocalDateTime leftAt;

    @Builder
    public ChatGroupUserEntity(ChatGroupEntity chatGroup, AccountEntity account, GroupUserType type) {
        this.chatGroup = chatGroup;
        this.account = account;
        this.type = type;
    }

    public ChatGroupUser toDomain() {
        return new ChatGroupUser(id, chatGroup.toDomain(), account.toDomain(), type);
    }

}
