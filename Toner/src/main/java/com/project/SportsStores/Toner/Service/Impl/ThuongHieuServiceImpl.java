package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.ThuongHieu;
import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {
    @Autowired
    ThuongHieuRepository rp;


    @Override
    public List<ThuongHieu> getAll() {
        return rp.findAll();
    }

    @Override
    public void update(ThuongHieu th) {
        rp.save(th);
    }

    @Override
    public void save(ThuongHieu th) {
        rp.save(th);
    }

    @Override
    public ThuongHieu getByID(Long id) {
        return rp.findById(id).get();
    }

    @Override
    public Page<ThuongHieu> pageOfTH(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<ThuongHieu> searchTH(String keyword, Pageable pageable) {
        return rp.searchTH(keyword, pageable);
    }
}
