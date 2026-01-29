package com.dolmengi.api.application.port.in.user;

import com.dolmengi.api.adapter.in.rest.user.dto.LoginRequest;

public interface LoginUseCase {

    String login(LoginRequest request);

}
