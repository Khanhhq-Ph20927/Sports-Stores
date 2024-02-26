package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface KhachHangService {


    void update(KhachHang khachHang);

    KhachHang getByID(Long id);

    Optional<KhachHang> findById(Long aLong);

    Page<KhachHang> pageOfKhachHang(Pageable pageable);

    Page<KhachHang> seacrch(String keyword,Pageable pageable);

    Page<KhachHang> fillterByGenderNoSearch(Pageable pageable,boolean gender);

    Page<KhachHang> fillterByDateNoSearch(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);

    Page<KhachHang> fillterByGenderAndSearch(Pageable pageable,String keyword,boolean gender);


}
