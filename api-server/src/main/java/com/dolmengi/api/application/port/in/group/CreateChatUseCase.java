package com.dolmengi.api.application.port.in.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateChatRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;

public interface CreateChatUseCase {

    CreateGroupResponse create(CreateChatRequest request);

}
