package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.NhanVien;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NhanVienService {

    List<NhanVien> getAll();

    Optional<NhanVien> findById(Long aLong);

    void save (NhanVien nv);
    
    Page<NhanVien> page(Pageable pageable);

    Page<NhanVien> SearchPage(Pageable pageable,String keyword);

    Page<NhanVien> SearchAllCustom(Pageable pageable,String keyword,int status,Long position);

}
