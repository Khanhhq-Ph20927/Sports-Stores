package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {

    @Query("select s from SanPhamChiTiet s where s.sp.tenSP like ?1 or s.sp.maSP like ?1 or s.ms.ten like ?1 or s.size like ?1")
    public Page<SanPhamChiTiet> search(String search, Pageable pageable);
}
