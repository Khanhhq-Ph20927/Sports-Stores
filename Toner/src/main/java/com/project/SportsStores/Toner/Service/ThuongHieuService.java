package com.project.SportsStores.Toner.Service;


import com.project.SportsStores.Toner.Model.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieu> getAll();

    void update(ThuongHieu th);

    void save(ThuongHieu th);

    ThuongHieu getByID(Long id);

    Page<ThuongHieu> pageOfTH(Pageable pageable);

    Page<ThuongHieu> searchTH(String keyword,Pageable pageable);
}
