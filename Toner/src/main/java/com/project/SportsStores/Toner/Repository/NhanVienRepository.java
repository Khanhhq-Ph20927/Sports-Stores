package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.NhanVien;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    @Override
    <S extends NhanVien> S saveAndFlush(S entity);

    @Override
    <S extends NhanVien> List<S> findAll(Example<S> example);

    @Override
    List<NhanVien> findAll();

    @Override
    <S extends NhanVien> S save(S entity);

    @Override
    Optional<NhanVien> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<NhanVien> findAll(Pageable pageable);

    @Query("Select nv from NhanVien nv where nv.email = :email")
    Optional<NhanVien> getByEmail(@Param("email") String email);

    @Query("SELECT nv from NhanVien nv where (:keyword is null or nv.maNV like %:keyword% or nv.hoTen like %:keyword% or nv.sdt like %:keyword% or nv.email like %:keyword%)")
    Page<NhanVien> SearchPage(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT nv from NhanVien nv where nv.trangThai=:status")
    Page<NhanVien> filterByStatusNoSearch(Pageable pageable, @Param("status") int status);

    @Query("SELECT nv from NhanVien nv where (:keyword is null or nv.maNV like %:keyword% or nv.hoTen like %:keyword% or nv.sdt like %:keyword% or nv.email like %:keyword%) and nv.trangThai=:status")
    Page<NhanVien> filterByStatusAndSearch(Pageable pageable,@Param("keyword") String keyword, @Param("status") int status);

    @Query("SELECT nv from NhanVien nv where nv.cv.id=:position")
    Page<NhanVien> filterByPositionNoSearch(Pageable pageable, @Param("position") Long position);

    @Query("SELECT nv from NhanVien nv where (:keyword is null or nv.maNV like %:keyword% or nv.hoTen like %:keyword% or nv.sdt like %:keyword% or nv.email like %:keyword%) and nv.cv.id=:position")
    Page<NhanVien> filterByPositionAndSearch(Pageable pageable,@Param("keyword") String keyword, @Param("position") Long position);

    @Query("SELECT nv from NhanVien nv where (nv.trangThai=:status and nv.cv.id=:position)")
    Page<NhanVien> filterByStatusAndPositionNoSearch(Pageable pageable, @Param("status") int status, @Param("position") Long position);

    @Query("SELECT nv from NhanVien nv where (:keyword is null or nv.maNV like %:keyword% or nv.hoTen like %:keyword% or nv.sdt like %:keyword% or nv.email like %:keyword%) and nv.trangThai=:status and nv.cv.id=:position")
    Page<NhanVien> SearchAndFilter(Pageable pageable, @Param("keyword") String keyword, @Param("status") int status, @Param("position") Long position);
}