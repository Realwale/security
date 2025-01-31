package com.keepo.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jwt_token")
public class JwtToken extends BaseEntity {

    @Column(name = "token", unique = true)
    private String token;
    @Column(unique = true)
    private String refreshToken;
    private boolean isExpired;
    private boolean isRevoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    private Date expiryDate;
}