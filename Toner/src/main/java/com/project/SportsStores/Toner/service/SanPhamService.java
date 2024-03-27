package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SanPhamService {

    List<SanPham> getAll();

    Page<SanPham> getPagination(Pageable pageable);

    Optional<SanPham> getById(Long id);

    boolean save(SanPham sp);

    boolean update(SanPham sp);

    boolean deleteById(Long id);

    Page<SanPham> seacrhProduct(String keyword,Pageable pageable);

    Page<SanPham> searchAndfilterByCollection (String keyword,Pageable pageable,String collection);

    Page<SanPham> searchAndfilterByStatus (String keyword,Pageable pageable,String status);

    Page<SanPham> searchAndfilterByStatusAndCollection (String keyword,Pageable pageable,String status,String collection);

    Page<SanPham> filterByStatusNoSearch(Pageable pageable,String status);

    Page<SanPham> filterByCollectionNoSearch(Pageable pageable,String collection);

    Page<SanPham> filterByStatusAndCollectionNoSearch(Pageable pageable,String status,String collection);

    Page<SanPham> filterByPrice (String keyword,Pageable pageable,String priceStart,String priceEnd);

    Page<SanPham> filterByStatusAndPrice (String keyword,Pageable pageable,String status,String priceStart,String priceEnd);

    Page<SanPham> filterByCollectionAndPrice (String keyword,Pageable pageable,String collection,String priceStart,String priceEnd);

    Page<SanPham> filterByAll (String keyword,Pageable pageable,String status,String collection,String priceStart,String priceEnd);

    //Create by khanhhq
    Page<SanPham> pageClient(Pageable pageable);

    //price
    Page<SanPham> price(Pageable pageable, String priceStart,  String priceEnd);

    //price+color
    Page<SanPham> priceAndFilterColor(Pageable pageable, String priceStart,  String priceEnd,  List<Integer> color);

    //price+size
    Page<SanPham> priceAndFilterSize(Pageable pageable, String priceStart,  String priceEnd,  List<String> size);

    //price+color+size
    Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, String priceStart,  String priceEnd,  List<Integer> color,  List<String> size);

    //color
    Page<SanPham> filterColor(Pageable pageable,  List<Integer> color);

    //size
    Page<SanPham> filterSize(Pageable pageable,  List<String> size);

    //color+size
    Page<SanPham> filterColorAndSize(Pageable pageable,  List<Integer> color,  List<String> size);

    Integer priceMax();

    //sport
    Page<SanPham> filterSport(Pageable pageable,  String sports);

    //brands
    Page<SanPham> filterBrand(Pageable pageable,  String brands);
}
