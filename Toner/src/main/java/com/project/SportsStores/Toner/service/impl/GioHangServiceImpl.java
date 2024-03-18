package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Repository.GioHangRepository;
import com.project.SportsStores.Toner.Service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository rp;

    @Override
    public void save(GioHang gioHang) {
        rp.save(gioHang);
    }

    @Override
    public GioHang getById(String id) {
        if (rp.findById(Long.parseLong(id)).isPresent()) {
            return rp.findById(Long.parseLong(id)).get();
        } else {
            return null;
        }
    }

    @Override
    public GioHang findByIdKH(Long id) {
        if(rp.findByIdKH(id).isPresent()) {
            return rp.findByIdKH(id).get();
        } else {
            return null;
        }
    }
}
