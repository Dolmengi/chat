package com.dolmengi.api.adapter.in.rest.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.DeleteChannelRequest;
import com.dolmengi.api.application.port.in.channel.DeleteChannelUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteChannelController {

    private final DeleteChannelUseCase useCase;

    @PostMapping("/api/deleteChannel")
    public ResponseEntity<Boolean> delete(@RequestBody @Valid DeleteChannelRequest request) {
        useCase.delete(request);

        return ResponseEntity.ok(true);
    }

}
