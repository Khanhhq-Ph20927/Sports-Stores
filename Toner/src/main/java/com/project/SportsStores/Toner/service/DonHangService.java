package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DonHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DonHangService {

    void delete(Long id);

    List<DonHang> getAll();

    Optional<DonHang> findById(Long id);

    void save(DonHang dh);

    Page<DonHang> page(Pageable pageable);
}