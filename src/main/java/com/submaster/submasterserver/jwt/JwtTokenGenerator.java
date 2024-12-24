package com.submaster.submasterserver.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.submaster.submasterserver.api.exception.SubMasterException;
import com.submaster.submasterserver.jwt.dto.JwtToken;
import com.submaster.submasterserver.jwt.dto.LoginUserInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private static final String JWT_UNKNOWN_MESSAGE = "JWT토큰이 수신되지 않았거나 형식이 맞지않습니다.";
    private static final String JWT_EXPIRED_MESSAGE = "JWT토큰이 만료되었습니다.";

    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 12;             // 12시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 30;      // 30일

    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper;

    public JwtToken generate(LoginUserInfo loginUserInfo) throws JsonProcessingException {
        String subject = objectMapper.writeValueAsString(loginUserInfo);
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return JwtToken.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public JwtToken generateJwtToken(String refreshToken) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        String accessToken = "";

        try {
            if (refreshToken != null && jwtTokenProvider.extractSubject(refreshToken)) {
                String subject = jwtTokenProvider.getUserPk(refreshToken);
                accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
                refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);
            }
        } catch (ExpiredJwtException e) {
            throw new SubMasterException(JWT_EXPIRED_MESSAGE, e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new SubMasterException(JWT_UNKNOWN_MESSAGE, e);
        }
        return JwtToken.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }
}
