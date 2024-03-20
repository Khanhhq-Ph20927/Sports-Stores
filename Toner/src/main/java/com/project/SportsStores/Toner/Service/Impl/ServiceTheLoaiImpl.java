package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.TheLoai;
import com.project.SportsStores.Toner.Repository.TheLoaiRepository;
import com.project.SportsStores.Toner.Service.ServiceTheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTheLoaiImpl implements ServiceTheLoai {
    @Autowired
    private TheLoaiRepository rp;

    @Override
    public List<TheLoai> findAll() {
        return rp.findAll();
    }
}
