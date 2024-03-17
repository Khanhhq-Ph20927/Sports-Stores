package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.AnhSanPham;

import java.util.List;

public interface AnhSanPhamService {

    void save(AnhSanPham asp);

    AnhSanPham getByIdProductDetail(String id);

    List<AnhSanPham> getByIdProduct(String id);

    List<AnhSanPham> getByIdProductAndColor(String id,String color);
}
