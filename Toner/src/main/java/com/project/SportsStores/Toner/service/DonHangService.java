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

    DonHang findByDH(String dh);

    void save(DonHang dh);

    Page<DonHang> page(Pageable pageable);

    Page<DonHang> filterByStatus(Pageable pageable,int status);

    Page<DonHang> filterByDate(Pageable pageable,String startDate, String endDate);

    Page<DonHang> filterByAll(Pageable pageable,int status,String startDate, String endDate);

    Page<DonHang> searchAndFilterByAll(Pageable pageable,String keyword, int status,
                                       String startDate, String endDate);

    Page<DonHang> search(Pageable pageable,String keyword);

    Page<DonHang> searchAndFilter(Pageable pageable,String keyword, int status);

    Page<DonHang> searchAndFilterByDate(Pageable pageable,String keyword, String startDate, String endDate);
}