package com.factura.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
    // Security configuration will be added here in the futureç
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                  authorize.requestMatchers("/api/productos/**").permitAll()
                          .anyRequest().authenticated();
                    //  authorize.anyRequest().authenticated();
                }
                ).httpBasic(Customizer.withDefaults());


        return http.build();
    }
}
