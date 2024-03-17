package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.GioHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Long> {

    @Override
    <S extends GioHang> S save(S entity);

    @Override
    Optional<GioHang> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<GioHang> findAll(Pageable pageable);

    @Query("SELECT gh FROM GioHang gh where gh.kh.id=:IdKH")
    Optional<GioHang> findByIdKH(@Param("IdKH") Long id);
}
