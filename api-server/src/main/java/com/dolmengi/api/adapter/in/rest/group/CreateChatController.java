package com.dolmengi.api.adapter.in.rest.group;

import com.dolmengi.api.adapter.in.rest.group.dto.CreateChatRequest;
import com.dolmengi.api.adapter.in.rest.group.dto.CreateGroupResponse;
import com.dolmengi.api.application.port.in.group.CreateChatUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateChatController {

    private final CreateChatUseCase useCase;

    @PostMapping("/api/createChat")
    public ResponseEntity<CreateGroupResponse> create(@RequestBody @Valid CreateChatRequest request) {
        CreateGroupResponse response = useCase.create(request);

        return ResponseEntity.ok(response);
    }

}
