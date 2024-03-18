package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.GioHang;



public interface GioHangService {

    void save(GioHang gioHang);

    GioHang getById(String id);

    GioHang findByIdKH(Long id);
}
