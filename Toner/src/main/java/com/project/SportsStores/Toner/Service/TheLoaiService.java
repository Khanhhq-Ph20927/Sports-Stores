package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.TheLoai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
@Service
public interface TheLoaiService {

    TheLoai create(TheLoai theLoai);

    void delete(Long id);

    Page<TheLoai> findAll(Pageable pageable);

    Optional<TheLoai> findById(Long id);
}
