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
    public List<SanPhamChiTiet> getAll() {
        return rp.findAll();
    }

    @Override
    public Page<SanPhamChiTiet> searchAndFilter(int page, String keyword, String color, String size) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("ngayTao").descending());
        Page<SanPhamChiTiet> pagination;
        if (keyword == null && color == null && size == null) {
            System.out.println("findAll");
            pagination = rp.findAll(pageable);
        } else if (keyword == null && color != null && size == null) {
            pagination = rp.FilterByColor(color, pageable);
        } else if (keyword == null && color == null && size != null) {
            pagination = rp.FilterBySize(size, pageable);
        } else if (keyword == null && color != null && size != null) {
            pagination = rp.FilterByAll(color, size, pageable);
        } else if (keyword != null && color == null && size == null) {
            pagination = rp.search("%" + keyword + "%", pageable);
        } else if (keyword != null && color != null && size == null) {
            pagination = rp.searchAndFilterByColor("%" + keyword + "%", color,pageable);
        } else if (keyword != null && color == null && size != null) {
            pagination = rp.searchAndFilterBySize("%" + keyword + "%", size,pageable);
        } else {
            pagination = rp.searchAndFilterAll("%" + keyword + "%", color, size,pageable);
        }
        return pagination;
    }

    @Override
    public Page<SanPhamChiTiet> pagination(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public List<SanPhamChiTiet> findListProductByColor(String id, String ms) {
        return rp.findListProductByColor(id,ms);
    }

    @Override
    public SanPhamChiTiet findIdProductByColorAndSize(String id, String ms, String size) {
        return rp.findIdProductByColorAndSize(id,ms,size);
    }
}
