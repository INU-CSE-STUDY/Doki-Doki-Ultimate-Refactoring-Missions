package com.capstone.webserver.common.config.jwt;

import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.common.response.exception.ExceptionCode;
import com.capstone.webserver.user.entity.UserRefreshToken;
import com.capstone.webserver.user.repository.UserRefreshTokenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;

@Service
public class TokenProvider {
    private final String secretKey;
    private final long expirationHours;
    private final long refreshExpirationDays;
    private final String issuer;
    private final long reissueLimit;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.expiration-hours}") long expirationHours,
            @Value("${jwt.refresh-expiration-days}") long refreshExpirationDays,
            @Value("${jwt.issuer}") String issuer,
            UserRefreshTokenRepository userRefreshTokenRepository) {
        this.secretKey = secretKey;
        this.expirationHours = expirationHours;
        this.refreshExpirationDays = refreshExpirationDays;
        this.issuer = issuer;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        reissueLimit = refreshExpirationDays;
    }

    public String createAccessToken(String userSpecification) {
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setSubject(userSpecification)
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(refreshExpirationDays, ChronoUnit.DAYS)))
                .compact();
    }

    public String recreateAccessToken(String oldAccessToken) throws JsonProcessingException {
        String subject = decodeJwtPayLoadSubjcet(oldAccessToken);
        userRefreshTokenRepository.findByIdAndReissueCountLessThan(Long.parseLong(subject.split(":")[0]), reissueLimit)
                .ifPresentOrElse(
                        UserRefreshToken::increaseReissueCount,
                        () -> { throw new CustomException(ExceptionCode.EXPIRED_TOKEN); }
                );

        return createAccessToken(subject);
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateRefreshToken(String refreshToken, String oldAccessToken) throws JsonProcessingException {
        validateAndParseToken(refreshToken);

        String userId = decodeJwtPayLoadSubjcet(oldAccessToken).split(":")[0];
        userRefreshTokenRepository.findByIdAndReissueCountLessThan(Long.parseLong(userId), reissueLimit)
                .filter(memberRefreshToken -> memberRefreshToken.validateRefreshToken(refreshToken))
                .orElseThrow(() -> new CustomException(ExceptionCode.EXPIRED_TOKEN));
    }

    private Jws<Claims> validateAndParseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token);
    }

    private String decodeJwtPayLoadSubjcet(String oldAccessToken) throws  JsonProcessingException {
        return objectMapper.readValue(
            new String(Base64.getDecoder().decode(oldAccessToken.split("\\.")[1]), StandardCharsets.UTF_8), Map.class
        ).get("sub").toString();
    }
}
