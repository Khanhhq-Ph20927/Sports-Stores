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
}
