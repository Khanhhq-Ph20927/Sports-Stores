package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamChiTietService {

    Page<SanPhamChiTiet> getSpct(Pageable pageable,String id);

    void save(SanPhamChiTiet spct);

    SanPhamChiTiet getById(String id);

    void update(SanPhamChiTiet spct);

    List<SanPhamChiTiet> getListByIdSp(String id);

    Page<SanPhamChiTiet> sanPhamChiTietBanTaiQuay(String search,Pageable pageable);

    Page<SanPhamChiTiet> pagination(Pageable pageable);
}
