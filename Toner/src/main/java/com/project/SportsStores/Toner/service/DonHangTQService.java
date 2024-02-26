package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DonHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonHangTQService {
    List<DonHang> getALL();

    boolean save(DonHang donHang);

    boolean delete(Long id);

    List<DonHang> getAllByStatus();

    Page<DonHang> pageOfDonHang(Pageable pageable);

    Page<DonHang> seacrhSellOff(String keyword, Pageable pageable);

    List<DonHang> getSizeBySatus0();
}
