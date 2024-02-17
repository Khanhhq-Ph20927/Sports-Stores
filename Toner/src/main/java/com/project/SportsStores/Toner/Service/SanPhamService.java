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

    Page<SanPham> filterByCollection (String keyword,Pageable pageable,String collection);

    Page<SanPham> filterByStatus (String keyword,Pageable pageable,String status);

    Page<SanPham> filterByStatusAndCollection (String keyword,Pageable pageable,String status,String collection);

    Page<SanPham> filterByStatusNoSearch(Pageable pageable,String status);

    Page<SanPham> filterByCollectionNoSearch(Pageable pageable,String collection);

    Page<SanPham> filterByStatusAndCollectionNoSearch(Pageable pageable,String status,String collection);

    Page<SanPham> filterByPrice (String keyword,Pageable pageable,String priceStart,String priceEnd);

    Page<SanPham> filterByStatusAndPrice (String keyword,Pageable pageable,String status,String priceStart,String priceEnd);

    Page<SanPham> filterByCollectionAndPrice (String keyword,Pageable pageable,String collection,String priceStart,String priceEnd);

    Page<SanPham> filterByAll (String keyword,Pageable pageable,String status,String collection,String priceStart,String priceEnd);
}
