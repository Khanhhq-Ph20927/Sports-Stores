package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DonHangService {

    void delete(Long id);

    List<DonHang> getAll();

    Optional<DonHang> findById(Long id);

    void save(DonHang dh);

    Page<DonHang> page(Pageable pageable);
}