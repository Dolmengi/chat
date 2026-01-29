package com.dolmengi.api.adapter.out.persistence.repository;

import com.dolmengi.api.adapter.out.persistence.entity.ChatChannelEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatChannelRepository extends JpaRepository<ChatChannelEntity, Long> {

    @Query("select cc from ChatChannel cc " +
            "join fetch cc.chatGroup cg " +
            "where cg.id = ?1 " +
            "and cc.deleted = false " +
            "order by cg.createdAt")
    List<ChatChannelEntity> findByChatGroup(Long groupId);

}
