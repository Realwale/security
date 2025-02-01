package com.keepo.springsecurity.repository;


import com.keepo.springsecurity.model.JwtToken;
import com.keepo.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String token);

    JwtToken findByUser(User appUser);

    boolean existsByUser(User appUser);

    JwtToken findByRefreshToken(String refreshToken);

    void deleteByUser(User user);
}
