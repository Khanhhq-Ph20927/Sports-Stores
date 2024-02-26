package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.SanPhamChiTietRepository;
import com.project.SportsStores.Toner.Service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository rp;

    @Override
    public Page<SanPhamChiTiet> getSpct(Pageable pageable, String id) {
        return rp.getSpctByIdSp(id, pageable);
    }

    @Override
    public void save(SanPhamChiTiet spct) {
        rp.saveAndFlush(spct);
    }

    @Override
    public SanPhamChiTiet getById(String id) {
        if (rp.findById(Long.valueOf(id)).isPresent()) {
            return rp.findById(Long.valueOf(id)).get();
        }
        return null;
    }

    @Override
    public void update(SanPhamChiTiet spct) {
        rp.saveAndFlush(spct);
    }

    @Override
    public List<SanPhamChiTiet> getListByIdSp(String id) {
        return rp.getListSpctByIdSp(id);
    }

    @Override
    public Page<SanPhamChiTiet> sanPhamChiTietBanTaiQuay(String search, Pageable pageable) {
        Page<SanPhamChiTiet> page = null;
        if (search == null) {
            Sort sort = Sort.by(Sort.Direction.DESC, "ngayTao");
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            page = rp.findAll(pageable);
        } else {
            page = rp.search("%" + search + "%", pageable);
        }
        return page;
    }

    @Override
    public Page<SanPhamChiTiet> pagination(Pageable pageable) {
        return rp.findAll(pageable);
    }
}
