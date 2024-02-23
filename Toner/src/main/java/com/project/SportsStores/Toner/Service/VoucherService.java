package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface VoucherService {

    public KhuyenMai create(KhuyenMai voucher);

    public void delete(Long id);

    public Page<KhuyenMai> findAll(Date start, Date end,Pageable pageable);

    public Optional<KhuyenMai> findById(Long id);


}