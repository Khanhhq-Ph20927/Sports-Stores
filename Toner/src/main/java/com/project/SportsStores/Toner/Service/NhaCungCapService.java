package com.project.SportsStores.Toner.Service;


import com.project.SportsStores.Toner.Model.NhaCungCap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface NhaCungCapService {

    List<NhaCungCap> getAll();

    void update(NhaCungCap ncc);

    void save(NhaCungCap ncc);

    NhaCungCap getByID(Long id);

    Page<NhaCungCap> pageOfNCC(Pageable pageable);

    Page<NhaCungCap> searchNCC(String keyword,Pageable pageable);
}