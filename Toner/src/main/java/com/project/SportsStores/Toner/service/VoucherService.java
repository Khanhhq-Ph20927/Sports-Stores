package com.project.SportsStores.Toner.service;

import com.project.SportsStores.Toner.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface VoucherService {

    public Voucher create(Voucher voucher);

    public void delete(Integer id);

    public Page<Voucher> findAll(Date start, Date end,Pageable pageable);

    public Optional<Voucher> findById(Integer id);


}