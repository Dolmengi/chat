package com.dolmengi.api.adapter.in.rest.user;

import com.dolmengi.api.commons.util.HttpServletUtils;
import com.dolmengi.common.domain.security.SessionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SessionController {

    @GetMapping("/api/session")
    public ResponseEntity<SessionContext> session() {
        SessionContext sessionContext = HttpServletUtils.getSessionContext();

        return ResponseEntity.ok().body(sessionContext);
    }

}
