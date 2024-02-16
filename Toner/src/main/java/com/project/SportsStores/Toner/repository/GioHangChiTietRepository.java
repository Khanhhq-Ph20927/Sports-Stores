package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import com.project.SportsStores.Toner.Model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {

    @Query("select g from GioHangChiTiet g where g.gh.kh.id = ?1")
    public Page<GioHangChiTiet> findByKhachHang(Long khachHangId, Pageable pageable);

    @Query("select g from GioHangChiTiet g where g.gh.kh.id = ?1 and(g.sp.tenSP like ?2 or g.sp.maSP like ?2)")
    public Page<GioHangChiTiet> findByKhachHangAndParam(Long khachHangId, String search, Pageable pageable);
}
