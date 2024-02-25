package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonHangChiTietService {

    Page<DonHangChiTiet> getById(String id, Pageable pageable);

    void save(DonHangChiTiet dhct);

    List<DonHangChiTiet> findByIdHD(String id);
}
