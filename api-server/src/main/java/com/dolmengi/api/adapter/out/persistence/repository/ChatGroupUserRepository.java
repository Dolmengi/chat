package com.dolmengi.api.adapter.out.persistence.repository;

import com.dolmengi.api.adapter.out.persistence.entity.ChatGroupUserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupUserRepository extends JpaRepository<ChatGroupUserEntity, Long> {

    @Query("select cgu from ChatGroupUser cgu " +
            "join fetch cgu.chatGroup cg " +
            "join fetch cgu.account a " +
            "where a.id = ?1 " +
            "and cg.deleted = false " +
            "order by cgu.createdAt")
    List<ChatGroupUserEntity> findByAccount(Long accountId);

    @Query("select cgu from ChatGroupUser cgu " +
            "join fetch cgu.chatGroup cg " +
            "join fetch cgu.account a " +
            "where cg.id = ?1 " +
            "and cg.deleted = false " +
            "order by cgu.createdAt")
    List<ChatGroupUserEntity> findByChatGroup(Long groupId);

    @Query("select cgu from ChatGroupUser cgu " +
            "join fetch cgu.account a " +
            "where cgu.chatGroup.id = ?1 " +
            "and a.id <> ?2 " +
            "and cgu.chatGroup.deleted = false " +
            "order by cgu.createdAt")
    List<ChatGroupUserEntity> findByChatGroupAndAccountNot(Long groupId, long accountId);

}
