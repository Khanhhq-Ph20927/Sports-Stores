package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SanPhamChiTietService {

    Page<SanPhamChiTiet> getSpct(Pageable pageable,String id);

    void save(SanPhamChiTiet spct);
}
