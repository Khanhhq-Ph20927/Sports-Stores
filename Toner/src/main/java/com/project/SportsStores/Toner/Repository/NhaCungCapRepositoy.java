package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.NhaCungCap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhaCungCapRepositoy extends JpaRepository<NhaCungCap,Long> {

    @Override
    <S extends NhaCungCap> S save(S entity);

    @Override
    Optional<NhaCungCap> findById(Long aLong);

    @Override
    Page<NhaCungCap> findAll(Pageable pageable);

    @Query("Select ncc from NhaCungCap ncc WHERE ncc.sdt LIKE %:keyword% or ncc.ten like  %:keyword% or ncc.email like  %:keyword% ")
    Page<NhaCungCap> searchNCC(@Param("keyword") String keyword, Pageable pageable);
}
