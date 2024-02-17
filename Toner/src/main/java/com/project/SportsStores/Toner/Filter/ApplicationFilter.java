package com.project.SportsStores.Toner.Filter;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationFilter {

    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Optional<NhanVien> nhanVien = nhanVienRepository.getByEmail(username);
            System.out.println(nhanVien.toString());
            if (nhanVien.isPresent()) {
                return nhanVien.get();
            }

            Optional<KhachHang> khachHang = khachHangRepository.getByEmail(username);
            System.out.println(khachHang.toString());
            if (khachHang.isPresent()) {
                return khachHang.get();
            }

            throw new UsernameNotFoundException("User not found");
        };
    }

//    @Bean
//    public UserDetailsService userDetailsService2(){
//        return username -> khachHangRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
