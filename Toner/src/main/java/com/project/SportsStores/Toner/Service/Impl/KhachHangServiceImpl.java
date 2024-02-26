package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository rp ;

    @Override
    public void update(KhachHang khachHang) {
        rp.save(khachHang);
    }

    @Override
    public KhachHang getByID(Long id) {
        return rp.findById(id).get();
    }

    @Override
    public Optional<KhachHang> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Page<KhachHang> pageOfKhachHang(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<KhachHang> seacrch(String keyword, Pageable pageable) {
        return rp.searchKhachHang(keyword,pageable);
    }

    @Override
    public Page<KhachHang> fillterByGenderNoSearch(Pageable pageable, boolean gender) {
        return rp.filterByGenderNoSearch(pageable,gender);
    }

    @Override
    public Page<KhachHang> fillterByDateNoSearch(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        return rp.findAllByNgayTaoBetween(pageable,startDate,endDate);
    }

    @Override
    public Page<KhachHang> fillterByGenderAndSearch(Pageable pageable, String keyword, boolean gender) {
        return rp.fillterByGenderAnSearch(pageable,keyword,gender);
    }


}
