package com.dolmengi.api.application.port.in.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.DeleteChannelRequest;

public interface DeleteChannelUseCase {

    void delete(DeleteChannelRequest request);

}
