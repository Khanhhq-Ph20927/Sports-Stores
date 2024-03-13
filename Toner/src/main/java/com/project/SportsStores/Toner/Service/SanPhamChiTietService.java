package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public interface SanPhamChiTietService {

    public Page<SanPhamChiTiet> sanPhamChiTietBanTaiQuay(String search,Pageable pageable);
}
