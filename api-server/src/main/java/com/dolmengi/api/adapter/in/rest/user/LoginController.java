package com.dolmengi.api.adapter.in.rest.user;

import com.dolmengi.api.adapter.in.rest.user.dto.LoginRequest;
import com.dolmengi.api.application.port.in.user.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginUseCase useCase;

    @PostMapping("/api/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        String token = useCase.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
    }

}
