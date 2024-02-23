package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Repository.VoucherRepository;
import com.project.SportsStores.Toner.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class VoucherServiceImp implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;
    @Override
    public KhuyenMai create(KhuyenMai voucher) {
        KhuyenMai result = voucherRepository.save(voucher);
        return result;
    }


    @Override
    public void delete(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Page<KhuyenMai> findAll(Date start, Date end, Pageable pageable) {
        if(start == null || end == null){
            start = Date.valueOf("2000-01-01");
            end = Date.valueOf("2200-01-01");
        }
        Page<KhuyenMai> page = voucherRepository.findByDate(start,end,pageable);
        return page;
    }

    @Override
    public Optional<KhuyenMai> findById(Long id) {
        Optional<KhuyenMai> ex = voucherRepository.findById(id);
        return ex;
    }


}
