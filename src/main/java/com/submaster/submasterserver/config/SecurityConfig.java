package com.submaster.submasterserver.config;

import com.submaster.submasterserver.entity.user.Role;
import com.submaster.submasterserver.jwt.JwtTokenProvider;
import com.submaster.submasterserver.jwt.filter.JwtAuthenticationFilter;
import com.submaster.submasterserver.jwt.filter.JwtExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(getPublicMatchers()).permitAll()
                        .anyRequest().hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/"))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionHandlerFilter(),
                        JwtAuthenticationFilter.class);

        return http.build();
    }

    private RequestMatcher[] getPublicMatchers() {
        return new RequestMatcher[]{
                new AntPathRequestMatcher("/welcome"),
                new AntPathRequestMatcher("/docs/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/js/**"),
        };
    }
}

