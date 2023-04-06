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

        // http.authorizeHttpRequests().requestMatchers("/register",
        // "/login").permitAll();

        http.authorizeHttpRequests(t -> t
                .requestMatchers(HttpMethod.POST, "/login")
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

        // http
        // .authorizeHttpRequests()
        // .requestMatchers(HttpMethod.POST, "/login").permitAll();

        // http.authorizeHttpRequests().requestMatchers("/products").hasAnyRole(Role.USER.name(),
        // Role.ADMIN.name());
        // http.authorizeHttpRequests().requestMatchers("/users").hasRole(Role.ADMIN.name());

        // http.authorizeHttpRequests().and().exceptionHandling(handling ->
        // handling.accessDeniedPage("/403"));

        // http.authorizeHttpRequests().and().formLogin(
        // login -> login.loginProcessingUrl("/j_spring_security_check")
        // .loginPage("/login")
        // .defaultSuccessUrl("/products")
        // .failureUrl("/login?error=True")
        // .usernameParameter("username")
        // .passwordParameter("password"))
        // .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));

        // http.authorizeHttpRequests().requestMatchers("/", "/sign-up", "/img/**",
        // "/css/**", "/js/**")
        // .permitAll()
        // .anyRequest().authenticated() //
        // .and()
        // .formLogin(login ->
        // login.loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?fail")
        // .permitAll())
        // .logout(logout ->
        // logout.logoutSuccessUrl("/home").invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID").permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
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