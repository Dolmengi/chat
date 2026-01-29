package com.dolmengi.api.adapter.in.rest.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateGroupUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateGroupController {

    private final CreateGroupUseCase useCase;

    @PostMapping("/api/createGroup")
    public ResponseEntity<CreateGroupResponse> create(@RequestBody CreateGroupRequest request) {
        CreateGroupResponse response = useCase.create(request);

        return ResponseEntity.ok(response);
    }

}
