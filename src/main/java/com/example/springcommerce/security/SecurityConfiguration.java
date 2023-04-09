package com.example.springcommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.springcommerce.emun.Role;

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
                // .requestMatchers(HttpMethod.POST, "/login")
                // .permitAll()
                .requestMatchers("/login/**")
                .permitAll()
                .requestMatchers("/register/**")
                .permitAll())
                .authorizeHttpRequests()
                .requestMatchers("/cart/**").hasAnyAuthority(Role.USER.name())
                .requestMatchers("/users").hasAnyAuthority(Role.ADMIN.name())
                .requestMatchers("/products/new").hasAnyAuthority(Role.ADMIN.name())
                .requestMatchers("/products/edit/{id}").hasAnyAuthority(Role.ADMIN.name())
                .requestMatchers("/products/delete/{id}").hasAnyAuthority(
                        Role.ADMIN.name())

                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .failureHandler(authenticationFailureHandler())
                        .successHandler(authenticationSuccessHandler())
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/home");
        return successHandler;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/login?error");
        return failureHandler;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/css/**", "/js/**",
                "/webjars/**");
    }
}