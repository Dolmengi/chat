package com.dolmengi.api.application.port.in.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelRequest;
import com.dolmengi.api.adapter.in.rest.channel.dto.CreateChannelResponse;

public interface CreateChannelUseCase {

    CreateChannelResponse create(CreateChannelRequest request);

}
