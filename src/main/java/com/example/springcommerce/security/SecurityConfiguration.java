//package com.example.springcommerce.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.NonNull;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Configuration
//@Component
//@RequiredArgsConstructor
//public class SecurityConfiguration  {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/sign-up", "/img/**", "/css/**", "/js/**").permitAll()
//                .anyRequest().authenticated() //
//                .and()
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/home")
//                        .failureUrl("/login?fail")
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/home")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }
//
////    private final JwtService jwtService;
////    @Override
////    protected void doFilterInternal(
////            @NonNull HttpServletRequest request,
////            @NonNull HttpServletResponse response,
////            @NonNull FilterChain filterChain) throws ServletException, IOException {
////        final String authorization = request.getHeader("Authorization");
////        final String jwt;
////        final String email;
////        if (authorization == null || authorization.startsWith("Bearer ")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////        jwt = authorization.substring(7);
////
////    }
//}