package com.dolmengi.api.adapter.in.rest.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelRequest;
import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelResponse;
import com.dolmengi.api.application.port.in.channel.CreateChannelUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateChannelController {

    private final CreateChannelUseCase useCase;

    @PostMapping("/api/createChannel")
    public ResponseEntity<CreateChannelResponse> create(@RequestBody @Valid CreateChannelRequest request) {
        CreateChannelResponse response = useCase.create(request);

        return ResponseEntity.ok(response);
    }

}
