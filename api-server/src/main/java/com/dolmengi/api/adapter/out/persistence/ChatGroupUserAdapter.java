package com.dolmengi.api.adapter.out.persistence;

import static com.dolmengi.common.exception.ExceptionCode.INVALID_PARAMETER;

import com.dolmengi.api.adapter.out.persistence.entity.AccountEntity;
import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupEntity;
import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupUserEntity;
import com.dolmengi.api.adapter.out.persistence.repository.AccountRepository;
import com.dolmengi.api.adapter.out.persistence.repository.ChatGroupRepository;
import com.dolmengi.api.adapter.out.persistence.repository.ChatGroupUserRepository;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.group.ChatGroup;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.group.GroupUserType;
import com.dolmengi.common.exception.ChatException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChatGroupUserAdapter implements ChatGroupUserPort {

    private final ChatGroupUserRepository repository;
    private final ChatGroupRepository groupRepository;
    private final AccountRepository accountRepository;

    @Override
    public ChatGroupUser createChatGroupUser(ChatGroup chatGroup, Account account, GroupUserType type) {
        ChatGroupEntity chatGroupEntity = groupRepository.findById(chatGroup.id()).orElseThrow(() -> new ChatException(INVALID_PARAMETER));
        AccountEntity accountEntity = accountRepository.findById(account.id()).orElseThrow(() -> new ChatException(INVALID_PARAMETER));

        ChatGroupUserEntity entity = ChatGroupUserEntity.builder()
                .chatGroup(chatGroupEntity)
                .account(accountEntity)
                .type(type)
                .build();

        return repository.save(entity).toDomain();
    }

    @Override
    public ChatGroupUser getChatGroupUser(Long id) {
        return repository.findById(id).map(ChatGroupUserEntity::toDomain).orElseThrow(() -> new ChatException(INVALID_PARAMETER));
    }

    @Override
    public List<ChatGroupUser> getChatGroupUserList(Account account) {
        List<ChatGroupUserEntity> entities = repository.findByAccount(account.id());

        return entities.stream().map(ChatGroupUserEntity::toDomain).toList();
    }

    @Override
    public List<ChatGroupUser> getChatGroupUserList(ChatGroup chatGroup) {
        List<ChatGroupUserEntity> entities = repository.findByChatGroup(chatGroup.id());

        return entities.stream().map(ChatGroupUserEntity::toDomain).toList();
    }

    @Override
    public List<ChatGroupUser> getChatGroupUserList(ChatGroup chatGroup, Account account) {
        List<ChatGroupUserEntity> entities = repository.findByChatGroupAndAccountNot(chatGroup.id(), account.id());

        return entities.stream().map(ChatGroupUserEntity::toDomain).toList();
    }

}
