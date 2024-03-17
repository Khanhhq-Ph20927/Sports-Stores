package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonHangChiTietService {

    Page<DonHangChiTiet> getById(String id, Pageable pageable);

    void save(DonHangChiTiet dhct);

    List<DonHangChiTiet> findByIdHD(String id);

    Page<DonHangChiTiet> getPagination(Pageable pageable, String id);

    DonHangChiTiet getById2(String id);

    void deleteById(String id);
}
