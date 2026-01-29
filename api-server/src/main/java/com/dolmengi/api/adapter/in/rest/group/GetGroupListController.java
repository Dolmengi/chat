package com.dolmengi.api.adapter.in.rest.group;

import com.dolmengi.api.adapter.in.rest.group.dto.GetGroupListResponse;
import com.dolmengi.api.application.port.in.group.GetGroupListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetGroupListController {

    private final GetGroupListUseCase useCase;

    @PostMapping("/api/getGroupList")
    public ResponseEntity<GetGroupListResponse> getGroupList() {
        GetGroupListResponse response = useCase.getGroupList();

        return ResponseEntity.ok(response);
    }

}
