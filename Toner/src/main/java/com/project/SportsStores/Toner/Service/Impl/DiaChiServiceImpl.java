package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.DiaChi;
import com.project.SportsStores.Toner.Repository.DiaChiRepository;
import com.project.SportsStores.Toner.Service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private DiaChiRepository rp;

    @Override
    public List<DiaChi> findAll(Sort sort) {
        return rp.findAll(sort);
    }

    @Override
    public List<DiaChi> getByIdKH(String id) {
        return rp.getByIdKH(id);
    }

    @Override
    public void deleteById(String id) {
        rp.deleteById(Long.parseLong(id));
    }

    @Override
    public DiaChi getById(String id) {
        if(rp.findById(Long.parseLong(id)).isPresent()) {
            return rp.findById(Long.parseLong(id)).get();
        } else {
            return null;
        }
    }

    @Override
    public void save(DiaChi diaChi) {
        rp.save(diaChi);
    }
}
