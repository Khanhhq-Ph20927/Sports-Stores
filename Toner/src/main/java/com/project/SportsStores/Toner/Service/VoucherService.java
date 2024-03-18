package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public interface VoucherService {

     KhuyenMai create(KhuyenMai voucher);

     void delete(Long id);

     Page<KhuyenMai> findAll(Date start, Date end,Pageable pageable);

     Optional<KhuyenMai> findById(Long id);


}