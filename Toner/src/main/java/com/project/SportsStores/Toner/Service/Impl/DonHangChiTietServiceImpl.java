package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.DonHangChiTietRepository;
import com.project.SportsStores.Toner.Repository.DonHangRepository;
import com.project.SportsStores.Toner.Repository.SanPhamChiTietRepository;
import com.project.SportsStores.Toner.Service.DonHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonHangChiTietServiceImpl implements DonHangChiTietService {

    @Autowired
    DonHangChiTietRepository rp;


    @Override
    public Page<DonHangChiTiet> getById(String id, Pageable pageable) {
        return rp.getByIdDH(id, pageable);
    }

    @Override
    public void save(DonHangChiTiet dhct) {
        rp.saveAndFlush(dhct);
    }

    @Override
    public List<DonHangChiTiet> findByIdHD(String id) {
        return rp.getByIdDHList(id);
    }

    @Override
    public Page<DonHangChiTiet> getPagination(Pageable pageable, String id) {
        return rp.getPaginationByIdDHList(id, pageable);
    }

    @Override
    public DonHangChiTiet getById2(String id) {
        if (rp.findById(Long.valueOf(id)).isPresent()) {
            return rp.findById(Long.valueOf(id)).get();
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(String id) {
        rp.deleteById(Long.parseLong(id));
    }
}
