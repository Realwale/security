package com.keepo.springsecurity.data.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private LocalDateTime loginTime;
    private String email;
    private String name;
    private long accessTokenExpiresIn;
    private long refreshTokenExpiry;
}