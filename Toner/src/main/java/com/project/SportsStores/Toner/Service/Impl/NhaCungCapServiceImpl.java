package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.NhaCungCap;
import com.project.SportsStores.Toner.Repository.NhaCungCapRepositoy;
import com.project.SportsStores.Toner.Service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    NhaCungCapRepositoy rp;

    @Override
    public List<NhaCungCap> getAll() {
        return rp.findAll();
    }

    @Override
    public void update(NhaCungCap ncc) {
        rp.save(ncc);
    }

    @Override
    public void save(NhaCungCap ncc) {
        rp.save(ncc);
    }

    @Override
    public NhaCungCap getByID(Long id) {
        return rp.findById(id).get();
    }

    @Override
    public Page<NhaCungCap> pageOfNCC(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<NhaCungCap> searchNCC(String keyword, Pageable pageable) {
        return rp.searchNCC(keyword,pageable);
    }
}
