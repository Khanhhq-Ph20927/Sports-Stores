package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    @Override
    <S extends GioHangChiTiet> S save(S entity);

    @Override
    Optional<GioHangChiTiet> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<GioHangChiTiet> findAll(Pageable pageable);
}
