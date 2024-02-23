package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.SanPhamChiTietRepository;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SanPhamChiTietServiceImp implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public Page<SanPhamChiTiet> sanPhamChiTietBanTaiQuay(String search, Pageable pageable) {
        Page<SanPhamChiTiet> page = null;
        if(search == null){
            page = sanPhamChiTietRepository.findAll(pageable);
        }
        else{
            page = sanPhamChiTietRepository.search("%"+search+"%",pageable);
        }
        return page;
    }
}
