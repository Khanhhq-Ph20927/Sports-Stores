package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamChiTietService {

    Page<SanPhamChiTiet> getSpct(Pageable pageable,String id);

    List<SanPhamChiTiet> getAll();

    void save(SanPhamChiTiet spct);

    SanPhamChiTiet getById(String id);

    void update(SanPhamChiTiet spct);

    List<SanPhamChiTiet> getListByIdSp(String id);

    Page<SanPhamChiTiet> searchAndFilter(int page,String keyword,String color,String size);

    Page<SanPhamChiTiet> Filter(int page,String color,String size,String id);

    Page<SanPhamChiTiet> pagination(Pageable pageable);

    List<SanPhamChiTiet> findListProductByColor(String id,String ms);

    SanPhamChiTiet findIdProductByColorAndSize(String id,String ms,String size);
}
