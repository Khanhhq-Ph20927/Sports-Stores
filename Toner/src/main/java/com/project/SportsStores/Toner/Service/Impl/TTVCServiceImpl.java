package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.ThongTinVanChuyen;
import com.project.SportsStores.Toner.Repository.ThongTinVanChuyenRepository;
import com.project.SportsStores.Toner.Service.TTVCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTVCServiceImpl implements TTVCService {
    @Autowired
    private ThongTinVanChuyenRepository rp;

    @Override
    public void save(ThongTinVanChuyen ttvc) {
        rp.saveAndFlush(ttvc);
    }

    @Override
    public List<ThongTinVanChuyen> findAll(Sort sort) {
        return rp.findAll(sort);
    }

    @Override
    public void deleteById(String id) {
        rp.deleteById(Long.valueOf(id));
    }

    @Override
    public ThongTinVanChuyen findById(String id) {
        if (rp.findById(Long.valueOf(id)).isPresent()) {
            return rp.findById(Long.valueOf(id)).get();
        } else {
            return null;
        }
    }
}
