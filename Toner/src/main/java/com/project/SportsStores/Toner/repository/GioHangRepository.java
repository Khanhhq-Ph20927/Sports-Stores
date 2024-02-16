package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.GioHang;
import com.project.SportsStores.Toner.Model.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    @Query("select g from GioHang g where g.kh.id = ?1")
    List<GioHang> findByKhachHang(Long idKh);
}
