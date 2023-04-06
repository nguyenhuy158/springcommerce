package com.example.springcommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.example.springcommerce.model.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@Component
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(t -> t
                .requestMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .requestMatchers("/login?error")
                .permitAll()
                .requestMatchers("/register")
                .permitAll())
                .authorizeHttpRequests()
                .requestMatchers("/users").hasAnyAuthority(Role.USER.name())
                .requestMatchers("/products").hasAnyAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureForwardUrl("/login?error")
                        // .successForwardUrl("/")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/css/**", "/js/**",
                "/webjars/**");
    }

    // private final JwtService jwtService;
    // @Override
    // protected void doFilterInternal(
    // @NonNull HttpServletRequest request,
    // @NonNull HttpServletResponse response,
    // @NonNull FilterChain filterChain) throws ServletException, IOException {
    // final String authorization = request.getHeader("Authorization");
    // final String jwt;
    // final String email;
    // if (authorization == null || authorization.startsWith("Bearer ")) {
    // filterChain.doFilter(request, response);
    // return;
    // }
    // jwt = authorization.substring(7);
    //
    // }
}