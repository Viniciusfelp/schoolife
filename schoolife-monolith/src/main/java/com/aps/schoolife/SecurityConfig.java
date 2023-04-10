package com.aps.schoolife;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher loginAndErrorRequestMatcher = request ->
                request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/error");

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers(loginAndErrorRequestMatcher).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                    .logoutSuccessUrl("/login");

        return http.build();
    }
}
