package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Repository.GioHangChiTietRepository;
import com.project.SportsStores.Toner.Service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository rp;

    @Override
    public Page<GioHangChiTiet> pagination(Pageable pageable) {
        return rp.findAll(pageable);
    }
}
