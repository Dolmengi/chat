package com.dolmengi.api.application.service.group;

import com.dolmengi.api.adapter.in.rest.group.dto.GetGroupListResponse;
import com.dolmengi.api.adapter.in.rest.group.dto.GroupInfo;
import com.dolmengi.api.application.port.in.group.GetGroupListUseCase;
import com.dolmengi.api.application.port.out.persistence.ChatGroupPort;
import com.dolmengi.api.application.port.out.persistence.ChatGroupUserPort;
import com.dolmengi.api.commons.util.HttpServletUtils;
import com.dolmengi.common.domain.account.Account;
import com.dolmengi.common.domain.group.ChatGroupUser;
import com.dolmengi.common.domain.security.SessionContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetGroupListService implements GetGroupListUseCase {

    private final ChatGroupPort groupPort;
    private final ChatGroupUserPort  groupUserPort;
    private final GroupInfoService groupInfoService;

    @Transactional
    @Override
    public GetGroupListResponse getGroupList() {
        SessionContext sessionContext = HttpServletUtils.getSessionContext();
        Account account = sessionContext.account();

        List<ChatGroupUser> groupUserList = groupUserPort.getChatGroupUserList(account);
        List<GroupInfo> groupInfoList = groupInfoService.groupInfoList(account, groupUserList);

        return new GetGroupListResponse(groupInfoList);
    }

}
