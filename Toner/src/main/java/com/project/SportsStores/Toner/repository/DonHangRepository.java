package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.DonHang;
import com.project.SportsStores.Toner.Model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository  extends JpaRepository<DonHang,Long> {
    @Override
    void flush();

    @Override
    <S extends DonHang> S saveAndFlush(S entity);

    @Override
    Page<DonHang> findAll(Pageable pageable);


//    @Query("SELECT dh FROM DonHang dh WHERE dh.maDonHang LIKE %:keyword%")

    @Query("SELECT dh FROM DonHang dh WHERE dh.trangThai =0")
    List<DonHang> getByStatus();

    @Query("SELECT dh FROM DonHang dh WHERE dh.maDonHang LIKE %:keyword% or dh.nv.hoTen like  %:keyword%" )
    Page<DonHang> searchDonHang(@Param("keyword") String keyword, Pageable pageable);
}
