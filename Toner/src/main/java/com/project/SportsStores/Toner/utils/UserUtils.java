package com.project.SportsStores.Toner.utils;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUtils {

    @Autowired
    KhachHangRepository khachHangRepository;

    public KhachHang getKhachHangWithJwt(){
        String email = SecurityUtils.getCurrentUserLogin().get();
        Optional<KhachHang> kh = khachHangRepository.getByEmail(email);
        if(kh.isPresent()){
            return kh.get();
        }
        return null;
    }


}
