package com.dolmengi.common.exception;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ExceptionResponse(int status, LocalDateTime timestamp, ErrorResponse exception) {

}
