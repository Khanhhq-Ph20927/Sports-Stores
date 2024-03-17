package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.AnhSanPham;
import com.project.SportsStores.Toner.Repository.AnhSanPhamRepository;
import com.project.SportsStores.Toner.Service.AnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnhSanPhamServiceImpl implements AnhSanPhamService {

    @Autowired
    private AnhSanPhamRepository rp;

    @Override
    public void save(AnhSanPham asp) {
        rp.saveAndFlush(asp);
    }

    @Override
    public AnhSanPham getByIdProductDetail(String id) {
        return rp.getByIdProductDetail(id);
    }

    @Override
    public List<AnhSanPham> getByIdProduct(String id) {
        return rp.getByIdProduct(id);
    }

    @Override
    public List<AnhSanPham> getByIdProductAndColor(String id, String color) {
        return rp.getByIdProductAndColor(id,color);
    }
}
