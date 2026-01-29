package com.dolmengi.api.application.port.in.channel;

import com.dolmengi.api.adapter.in.rest.channel.dto.UpdateChannelRequest;
import com.dolmengi.api.adapter.in.rest.channel.dto.UpdateChannelResponse;

public interface UpdateChannelUseCase {

    UpdateChannelResponse update(UpdateChannelRequest request);

}
