package com.dolmengi.api.adapter.in.rest.message;

import com.dolmengi.api.application.port.in.message.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SendMessageController {

    private final SendMessageUseCase useCase;

    @PostMapping("/api/sendMessage")
    public ResponseEntity<Void> sendMessage() {
        useCase.sendMessage();

        return ResponseEntity.ok().build();
    }

}
