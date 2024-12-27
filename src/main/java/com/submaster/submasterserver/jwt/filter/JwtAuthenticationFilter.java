package com.submaster.submasterserver.jwt.filter;

import com.submaster.submasterserver.jwt.JwtTokenProvider;
import com.submaster.submasterserver.jwt.exception.JwtTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String JWT_UNKNOWN_MESSAGE = "JWT토큰이 수신되지 않았거나 형식이 맞지않습니다.";
    private static final String JWT_EXPIRED_MESSAGE = "JWT토큰이 만료되었습니다.";

    private final JwtTokenProvider jwtTokenProvider;

    private static final String[] excludePath = {
            "/welcome",
            "/docs",
            "/api/v1"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(request);
            if (token != null && jwtTokenProvider.extractSubject(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
            throw new JwtTokenException(JWT_UNKNOWN_MESSAGE);
        } catch (ExpiredJwtException e) {
            throw new JwtTokenException(JWT_EXPIRED_MESSAGE, e);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtTokenException(JWT_UNKNOWN_MESSAGE, e);
        }
    }
}
