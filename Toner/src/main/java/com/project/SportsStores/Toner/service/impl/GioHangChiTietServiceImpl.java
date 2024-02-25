package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Repository.GioHangChiTietRepository;
import com.project.SportsStores.Toner.Service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    private GioHangChiTietRepository rp;

    @Override
    public Page<GioHangChiTiet> pagination(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<GioHangChiTiet> getByIdGH(String id, Pageable pageable) {
        return rp.getByIdGH(id, pageable);
    }

    @Override
    public void save(GioHangChiTiet gioHangChiTiet) {
        rp.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet getById(String id) {
        if(rp.findById(Long.parseLong(id)).isPresent()){
            return rp.findById(Long.parseLong(id)).get();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        rp.deleteById(Long.parseLong(id));
    }

    @Override
    public List<GioHangChiTiet> getByIdGHList(String id) {
        return rp.getByIdGHList(id);
    }

    @Override
    public void deleteAll() {
        rp.deleteAll();
    }
}
