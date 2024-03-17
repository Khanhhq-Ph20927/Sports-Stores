package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.GioHang;

import java.util.Optional;

public interface GioHangService {

    void save(GioHang gioHang);

    GioHang getById(String id);

    Optional<GioHang> findByIdKH(Long id);
}
