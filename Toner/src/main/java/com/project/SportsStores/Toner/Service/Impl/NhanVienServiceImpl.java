package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepository repository;

    @Override
    public List<NhanVien> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<NhanVien> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public void save(NhanVien nv) {
        repository.save(nv);
    }

    @Override
    public Page<NhanVien> page(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public Page<NhanVien> SearchPage(Pageable pageable, String keyword) {
        return repository.SearchPage(pageable, keyword);
    }

    @Override
    public Page<NhanVien> filterByStatusNoSearch(Pageable pageable, int status) {
        return repository.filterByStatusNoSearch(pageable, status);
    }

    @Override
    public Page<NhanVien> filterByStatusAndSearch(Pageable pageable, String keyword, int status) {
        return repository.filterByStatusAndSearch(pageable,keyword,status);
    }

    @Override
    public Page<NhanVien> filterByPositionNoSearch(Pageable pageable, Long position) {
        return repository.filterByPositionNoSearch(pageable, position);
    }

    @Override
    public Page<NhanVien> filterByPositionAndSearch(Pageable pageable, String keyword, Long position) {
        return repository.filterByPositionAndSearch(pageable,keyword,position);
    }

    @Override
    public Page<NhanVien> filterByStatusAndPositionNoSearch(Pageable pageable, int status, Long position) {
        return repository.filterByStatusAndPositionNoSearch(pageable, status, position);
    }

    @Override
    public Page<NhanVien> SearchAndFilter(Pageable pageable, String keyword, int status, Long position) {
        return repository.SearchAndFilter(pageable, keyword, status, position);
    }

}