package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Repository.DonHangRepository;
import com.project.SportsStores.Toner.Service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    DonHangRepository donHangRepositoty;


    @Override
    public void delete(Long id) {
        donHangRepositoty.deleteById(id);
    }

    @Override
    public List<DonHang> getAll() {
        return donHangRepositoty.findAll();
    }

    @Override
    public Optional<DonHang> findById(Long id) {
        return donHangRepositoty.findById(id);
    }

    @Override
    public void save(DonHang dh) {
        donHangRepositoty.save(dh);
    }

    @Override
    public Page<DonHang> page(Pageable pageable) {
        return donHangRepositoty.findAllBut0(pageable);
    }

    @Override
    public Page<DonHang> filterByStatus(Pageable pageable, int status) {
        return donHangRepositoty.filterByStatus(status, pageable);
    }

    @Override
    public Page<DonHang> search(Pageable pageable, String keyword) {
        return donHangRepositoty.searchByName(keyword, pageable);
    }

    @Override
    public Page<DonHang> searchAndFilter(Pageable pageable, String keyword, int status) {
        return donHangRepositoty.searchAndFilter(status, keyword, pageable);
    }

    @Override
    public Page<DonHang> filterByDate(Pageable pageable, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate + "T00:00:00", dateFormatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate + "T23:59:59", dateFormatter);
        return donHangRepositoty.filterByDate(dateTimeStart, dateTimeEnd, pageable);
    }

    @Override
    public Page<DonHang> searchAndFilterByAll(Pageable pageable, String keyword, int status, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate + "T00:00:00", dateFormatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate + "T23:59:59", dateFormatter);
        return donHangRepositoty.searchAndFilterByAll(status, dateTimeStart, dateTimeEnd, keyword, pageable);
    }

    @Override
    public Page<DonHang> searchAndFilterByDate(Pageable pageable, String keyword, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate + "T00:00:00", dateFormatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate + "T23:59:59", dateFormatter);
        return donHangRepositoty.searchAndFilterByDate(keyword, dateTimeStart, dateTimeEnd, pageable);
    }

    @Override
    public Page<DonHang> filterByAll(Pageable pageable, int status, String startDate, String endDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTimeStart = LocalDateTime.parse(startDate + "T00:00:00", dateFormatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endDate + "T23:59:59", dateFormatter);
        return donHangRepositoty.filterByDateAndStatus(dateTimeStart, dateTimeEnd, status, pageable);
    }

    @Override
    public DonHang findByDH(String dh) {
        if (donHangRepositoty.findByDH(dh).isPresent()) {
            return donHangRepositoty.findByDH(dh).get();
        } else {
            return null;
        }
    }
}