package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.SanPhamChiTietRepository;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public SanPhamChiTiet getById(String id) {
        if(rp.findById(Long.valueOf(id)).isPresent()){
            return rp.findById(Long.valueOf(id)).get();
        }
        return null;
    }

    @Override
    public void update(SanPhamChiTiet spct) {
        rp.saveAndFlush(spct);
    }

    @Override
    public List<SanPhamChiTiet> getListByIdSp(String id) {
        return rp.getListSpctByIdSp(id);
    }
}
