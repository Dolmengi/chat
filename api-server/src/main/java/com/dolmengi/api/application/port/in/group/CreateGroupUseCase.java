package com.dolmengi.api.application.port.in.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;

public interface CreateGroupUseCase {

    CreateGroupResponse create(CreateGroupRequest request);

}
