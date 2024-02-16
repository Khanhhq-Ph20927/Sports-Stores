package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.GioHang;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GioHangService {

    public List<GioHang> findByKhachHang(Long idKh);

}
