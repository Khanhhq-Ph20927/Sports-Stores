package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.DonHangRepository;
import com.project.SportsStores.Toner.Service.DonHangTQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangTQServiceImpl implements DonHangTQService {

    @Autowired
    private DonHangRepository dhrp;

    @Override
    public List<DonHang> getALL() {
        return dhrp.findAll();
    }

    @Override
    public boolean save(DonHang donHang) {
        dhrp.saveAndFlush(donHang);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        dhrp.deleteById(id);
        return true;
    }

    @Override
    public List<DonHang> getAllByStatus() {
        return dhrp.getByStatus();
    }

    @Override
    public Page<DonHang> pageOfDonHang(Pageable pageable) {
        return dhrp.getAllByStatusEquals0(pageable);
    }

    @Override
    public Page<DonHang> seacrhSellOff(String keyword, Pageable pageable) {
        return dhrp.searchDonHang(keyword, pageable);
    }

    @Override
    public List<DonHang> getSizeBySatus0() {
        return dhrp.getSizeByStatus0();
    }
}
