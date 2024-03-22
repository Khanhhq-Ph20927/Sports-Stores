package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.TheLoai;

import java.util.List;

public interface ServiceTheLoai {

    List<TheLoai> findAll();
    TheLoai getTheLoaiById(Long id);
    TheLoai createTheLoai(TheLoai theLoai);
    TheLoai updateTheLoai(Long id, TheLoai theLoai);
    void deleteTheLoai(Long id);
}
