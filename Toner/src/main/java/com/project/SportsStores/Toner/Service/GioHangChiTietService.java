package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GioHangChiTietService {

    Page<GioHangChiTiet> pagination(Pageable pageable);
}
