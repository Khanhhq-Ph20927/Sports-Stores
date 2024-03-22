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

    @Override
    public TheLoai getTheLoaiById(Long id) {
        return rp.findById(id).orElse(null);
    }

    @Override
    public TheLoai createTheLoai(TheLoai theLoai) {
        return rp.save(theLoai);
    }

    @Override
    public TheLoai updateTheLoai(Long id, TheLoai theLoai) {
        if (rp.existsById(id)) {
            theLoai.setId(id);
            return rp.save(theLoai);
        }
        return null;
    }

    @Override
    public void deleteTheLoai(Long id) {
        rp.deleteById(id);
    }
}
