package com.project.SportsStores.Toner.service.impl;

import com.project.SportsStores.Toner.exception.MessageException;
import com.project.SportsStores.Toner.model.Voucher;
import com.project.SportsStores.Toner.repository.VoucherRepository;
import com.project.SportsStores.Toner.service.VoucherService;
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
    public Voucher create(Voucher voucher) {
        Voucher result = voucherRepository.save(voucher);
        return result;
    }


    @Override
    public void delete(Integer id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public Page<Voucher> findAll(Date start, Date end, Pageable pageable) {
        if(start == null || end == null){
            start = Date.valueOf("2000-01-01");
            end = Date.valueOf("2200-01-01");
        }
        Page<Voucher> page = voucherRepository.findByDate(start,end,pageable);
        return page;
    }

    @Override
    public Optional<Voucher> findById(Integer id) {
        Optional<Voucher> ex = voucherRepository.findById(id);
        return ex;
    }


}
