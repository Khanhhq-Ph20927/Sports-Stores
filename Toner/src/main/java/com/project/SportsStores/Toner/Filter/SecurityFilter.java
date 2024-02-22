package com.project.SportsStores.Toner.Filter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityFilter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class).csrf(AbstractHttpConfigurer::disable);
//        http.authorizeRequests()
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/api/v2/auth/**").permitAll()
//                .requestMatchers("/index/home").permitAll()
//                .requestMatchers("/api/admin/product/**").permitAll()
//                .requestMatchers("/api/admin/product_detail/**").permitAll()
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/admin/**").permitAll()
//                .requestMatchers("/assets/**").permitAll()
//                .requestMatchers("/assets/images/**").permitAll()
//                .requestMatchers("/templates/**").permitAll()
//                .requestMatchers("/api/register/**").permitAll()
////                .requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
//                .requestMatchers("/api/staff/**").hasAnyAuthority("STAFF")
//                .requestMatchers("/api/customer/**").hasAnyAuthority("CUSTOMER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/auth/auth-signin-basic")
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
