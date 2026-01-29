package com.dolmengi.api.adapter.in.rest.group.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateChatRequest(@NotEmpty List<String> userIds) {

}
