package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet,Long> {
    @Override
    <S extends SanPhamChiTiet> S saveAndFlush(S entity);

    @Override
    List<SanPhamChiTiet> findAll();

    @Override
    <S extends SanPhamChiTiet> S save(S entity);

    @Override
    Optional<SanPhamChiTiet> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<SanPhamChiTiet> findAll(Sort sort);

    @Query("select spct from SanPhamChiTiet spct where spct.sp.id = :id")
    Page<SanPhamChiTiet> getSpctByIdSp(@Param("id") String id, Pageable pageable);

    @Query("select spct from SanPhamChiTiet spct where spct.sp.id = :id")
    List<SanPhamChiTiet> getListSpctByIdSp(@Param("id") String id);
}
