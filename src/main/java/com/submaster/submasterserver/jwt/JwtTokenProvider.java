package com.submaster.submasterserver.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.jwt.dto.LoginUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final ObjectMapper objectMapper;
    private final Key key;

    public JwtTokenProvider(@Value("${token.secret}") String secretKey, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(String subject, Date expiredAt) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean extractSubject(String accessToken) {
        Claims claims = parseClaims(accessToken);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String headerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer ")) {
            return headerToken.substring(7).trim();
        }
        return null;
    }

    public String getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) throws JsonProcessingException {
        LoginUserInfo loginUserInfo = objectMapper.readValue(this.getUserPk(token), LoginUserInfo.class);
        return new UsernamePasswordAuthenticationToken(loginUserInfo, "", Collections.singletonList(new SimpleGrantedAuthority(Role.USER.name())));
    }
}
