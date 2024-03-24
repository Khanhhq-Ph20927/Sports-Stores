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
        http.authorizeRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v2/auth/**").permitAll()
                .requestMatchers("/index/home").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/assets/images/**").permitAll()
                .requestMatchers("/templates/**").permitAll()
                .requestMatchers("/api/register/**").permitAll()
                .requestMatchers("/api/admin/customer/update/**").permitAll()
                .requestMatchers("/api/admin/staff/add").permitAll()
                .requestMatchers("/api/admin/staff/update/**").permitAll()
                .requestMatchers("/api/admin/trademark/update/**").permitAll()
                .requestMatchers("/client/product").permitAll()
                .requestMatchers("/api/client/product/priceMax").permitAll()
                .requestMatchers("/api/client/product/page/**").permitAll()
                .requestMatchers("/api/client/product_detail/picture/**").permitAll()
                .requestMatchers("/api/client/product_detail/detailPD/**").permitAll()
                .requestMatchers("/api/client/product_detail/detail/**").permitAll()
                .requestMatchers("/api/client/product_detail/picture-detail/**").permitAll()
                .requestMatchers("/api/client/cart_detail/add/**").permitAll()
                .requestMatchers("/api/client/product/page/**").permitAll()
                .requestMatchers("/public/voucher/find-by-id").permitAll()
                .requestMatchers("//admin/voucher/findAll-page").permitAll()
                .requestMatchers("/shop-cart").permitAll()
                .requestMatchers("/order").permitAll()
                .requestMatchers("/api/client/order/**").hasAnyAuthority("CUSTOMER")
                .requestMatchers("/api/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth-signin-basic").permitAll()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
