package net.croods.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/public/**").permitAll() 
                .anyRequest().authenticated() 
            )
            .formLogin((form) -> form
                .permitAll()                         
            )
            .logout((logout) -> logout
                .permitAll()                       
            );

        return http.build();
    }
}
