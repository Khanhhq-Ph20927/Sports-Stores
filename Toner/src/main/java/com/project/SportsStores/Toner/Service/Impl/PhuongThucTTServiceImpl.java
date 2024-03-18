package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.PhuongThucThanhToan;
import com.project.SportsStores.Toner.Repository.PhuongThucThanhToanRepository;
import com.project.SportsStores.Toner.Service.PhuongThucTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhuongThucTTServiceImpl implements PhuongThucTTService {
    @Autowired
    private PhuongThucThanhToanRepository rp;
    @Override
    public PhuongThucThanhToan getById(String id) {
        return rp.findById(Long.valueOf(id)).get();
    }
}
