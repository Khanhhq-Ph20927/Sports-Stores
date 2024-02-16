package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Repository.GioHangRepository;
import com.project.SportsStores.Toner.Service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GioHangServiceImp implements GioHangService {

    @Autowired
    private GioHangRepository gioHangRepository;


    @Override
    public List<GioHang> findByKhachHang(Long idKh) {
        List<GioHang> list = gioHangRepository.findByKhachHang(idKh);
        return list;
    }
}
