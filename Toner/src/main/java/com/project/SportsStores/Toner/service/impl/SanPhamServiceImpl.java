package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Repository.SanPhamRepository;
import com.project.SportsStores.Toner.Service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {


    @Autowired
    private SanPhamRepository rp;

    @Override
    public List<SanPham> getAll() {
        return rp.findAll();
    }

    @Override
    public Page<SanPham> getPagination(Pageable pageable) {
        return rp.findAll(pageable);
    }

    @Override
    public Page<SanPham> searchAndfilterByCollection(String keyword, Pageable pageable, String collection) {
        return rp.searchAndFilterProductByCollection(keyword,pageable,collection);
    }

    @Override
    public Page<SanPham> searchAndfilterByStatus(String keyword, Pageable pageable, String status) {
        return rp.searchAndFilterProductByStatus(keyword, pageable, status);
    }

    @Override
    public Page<SanPham> searchAndfilterByStatusAndCollection(String keyword, Pageable pageable, String status, String collection) {
        return rp.searchAndFilterProductByStatusAndCollection(pageable,keyword,status,collection);
    }

    @Override
    public Page<SanPham> filterByPrice(String keyword, Pageable pageable, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByPrice(keyword, pageable, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> filterByStatusAndPrice(String keyword, Pageable pageable, String status, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByStatusAndPrice(keyword,pageable,status,priceStart,priceEnd);
    }

    @Override
    public Page<SanPham> filterByCollectionAndPrice(String keyword, Pageable pageable, String collection, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByCollectionAndPrice(keyword,pageable,collection,priceStart,priceEnd);
    }

    @Override
    public Page<SanPham> filterByAll(String keyword, Pageable pageable, String status, String collection, String priceStart, String priceEnd) {
        return rp.searchAndFilterProductByAll(keyword, pageable, status, collection, priceStart, priceEnd);
    }

    @Override
    public Optional<SanPham> getById(Long id) {
        return rp.findById(id);
    }

    @Override
    public boolean save(SanPham sp) {
        rp.saveAndFlush(sp);
        return true;
    }

    @Override
    public boolean update(SanPham sp) {
        rp.saveAndFlush(sp);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        rp.deleteById(id);
        return true;
    }

    @Override
    public Page<SanPham> seacrhProduct(String keyword,Pageable pageable) {
        return rp.searchProduct(keyword,pageable);
    }

    @Override
    public Page<SanPham> filterByStatusNoSearch(Pageable pageable, String status) {
        return rp.filterProductByStatus(pageable,status);
    }

    @Override
    public Page<SanPham> filterByCollectionNoSearch(Pageable pageable, String collection) {
        return rp.filterProductByCollection(pageable,collection);
    }

    @Override
    public Page<SanPham> filterByStatusAndCollectionNoSearch(Pageable pageable, String status, String collection) {
        return rp.filterProductByStatusAndCollection(pageable,status,collection);
    }

    @Override
    public Page<SanPham> pageClient(Pageable pageable) {
        return rp.pageClient(pageable);

    }

    @Override
    public Page<SanPham> price(Pageable pageable, String priceStart, String priceEnd) {
        return rp.price(pageable, priceStart, priceEnd);
    }

    @Override
    public Page<SanPham> priceAndFilterColor(Pageable pageable, String priceStart, String priceEnd, List<Integer> color) {
        return rp.priceAndFilterColor(pageable, priceStart, priceEnd, color);
    }

    @Override
    public Page<SanPham> priceAndFilterSize(Pageable pageable, String priceStart, String priceEnd, List<String> size) {
        return rp.priceAndFilterSize(pageable, priceStart, priceEnd, size);
    }

    @Override
    public Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, String priceStart, String priceEnd, List<Integer> color, List<String> size) {
        return rp.priceAndFilterColorAndSize(pageable, priceStart, priceEnd, color, size);
    }

    @Override
    public Page<SanPham> filterColor(Pageable pageable, List<Integer> color) {
        return rp.filterColor(pageable, color);
    }

    @Override
    public Page<SanPham> filterSize(Pageable pageable, List<String> size) {
        return rp.filterSize(pageable, size);
    }

    @Override
    public Page<SanPham> filterColorAndSize(Pageable pageable, List<Integer> color, List<String> size) {

        return rp.filterColorAndSize(pageable, color, size);
    }

    @Override
    public Integer priceMax() {
        return rp.priceMax();
    }

    @Override
    public Page<SanPham> filterSport(Pageable pageable, String sports) {
        return rp.filterSport(sports,pageable);
    }

    @Override
    public Page<SanPham> filterBrand(Pageable pageable, String brands) {
        return rp.filterBrands(brands,pageable);
    }
}
