package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.SanPhamChiTietRepository;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository rp;

    @Override
    public Page<SanPhamChiTiet> getSpct(Pageable pageable, String id) {
        return rp.getSpctByIdSp(id, pageable);
    }

    @Override
    public void save(SanPhamChiTiet spct) {
        rp.saveAndFlush(spct);
    }
}
