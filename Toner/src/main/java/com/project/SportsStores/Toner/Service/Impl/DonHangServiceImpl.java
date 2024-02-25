package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.DonHangRepository;
import com.project.SportsStores.Toner.Service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    DonHangRepository donHangRepositoty;


    @Override
    public void delete(Long id) {
        donHangRepositoty.deleteById(id);
    }

    @Override
    public List<DonHang> getAll() {
        return donHangRepositoty.findAll();
    }

    @Override
    public Optional<DonHang> findById(Long id) {
        return donHangRepositoty.findById(id);
    }

    @Override
    public void save(DonHang dh) {
        donHangRepositoty.save(dh);
    }

    @Override
    public Page<DonHang> page(Pageable pageable) {
        return donHangRepositoty.findAll(pageable);
    }


}