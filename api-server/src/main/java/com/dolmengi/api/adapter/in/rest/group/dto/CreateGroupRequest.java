package com.dolmengi.api.adapter.in.rest.group.dto;

import java.util.List;

public record CreateGroupRequest(String name, List<String> userId) {

}
