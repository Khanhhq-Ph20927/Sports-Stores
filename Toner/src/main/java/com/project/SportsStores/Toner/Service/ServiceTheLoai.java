package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.TheLoai;

import java.util.List;

public interface ServiceTheLoai {

    List<TheLoai> findAll();

    TheLoai findById(String id);
}
