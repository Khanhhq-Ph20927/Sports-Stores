package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.DonHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonHangChiTietRepository extends JpaRepository<DonHangChiTiet, Long> {

    @Override
    <S extends DonHangChiTiet> S save(S entity);

    @Override
    <S extends DonHangChiTiet> S saveAndFlush(S entity);

    @Override
    Optional<DonHangChiTiet> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<DonHangChiTiet> findAll(Pageable pageable);

    @Query("select dhct from DonHangChiTiet dhct where dhct.dh.id = :id")
    Page<DonHangChiTiet> getByIdDH(@Param("id") String id, Pageable pageable);

    @Query("select dhct from DonHangChiTiet dhct where dhct.dh.id = :id")
    List<DonHangChiTiet> getByIdDHList(@Param("id") String id);
}
