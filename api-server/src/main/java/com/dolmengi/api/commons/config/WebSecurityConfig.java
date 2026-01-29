package com.dolmengi.api.commons.config;

import static com.dolmengi.common.constants.UserRole.ADMIN;
import static com.dolmengi.common.constants.UserRole.USER;

import com.dolmengi.api.commons.filter.AuthenticationFilter;
import com.dolmengi.api.commons.security.ChatAccessDeniedHandler;
import com.dolmengi.api.commons.security.ChatAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final ChatAuthenticationEntryPoint authenticationEntryPoint;
    private final ChatAccessDeniedHandler accessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {
            "/actuator/**", "/favicon.ico", "/error", "/", "/health/check", "/api/login", "/console/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.addAllowedOriginPattern("*");
                        config.addAllowedHeader("*");
                        config.addAllowedMethod("*");
                        config.setAllowCredentials(true);

                        return config;
                }))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers("/api/admin/**").hasRole(ADMIN.name())
                                .requestMatchers("/api/**").hasAnyRole(ADMIN.name(), USER.name())
                                .anyRequest().authenticated()
        );

        return http.build();
    }

}
