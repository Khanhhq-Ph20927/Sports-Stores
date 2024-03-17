package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {
    @Override
    <S extends ThuongHieu> S save(S entity);

    @Override
    Optional<ThuongHieu> findById(Long aLong);

    @Override
    Page<ThuongHieu> findAll(Pageable pageable);

    @Query("Select th from ThuongHieu th WHERE th.ten LIKE %:keyword% or th.email like  %:keyword% ")
    Page<ThuongHieu> searchTH(@Param("keyword") String keyword, Pageable pageable);
}
