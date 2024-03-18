package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.KhachHang;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    @Override
    <S extends KhachHang> S saveAndFlush(S entity);

    @Override
    <S extends KhachHang> List<S> findAll(Example<S> example);

    @Override
    <S extends KhachHang> List<S> findAll(Example<S> example, Sort sort);

    @Override
    List<KhachHang> findAll();

    @Override
    <S extends KhachHang> S save(S entity);

    @Override
    Optional<KhachHang> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<KhachHang> findAll(Pageable pageable);

    @Query("Select kh from KhachHang kh where kh.email = :email")
    Optional<KhachHang> getByEmail(@Param("email")String email);

    @Query("Select kh from KhachHang kh WHERE kh.sdt LIKE %:keyword% or kh.maKH like  %:keyword% or kh.hoTen like  %:keyword% " +
            "or kh.email LIKE %:keyword% " )
    Page<KhachHang> searchKhachHang(@Param("keyword") String keyword, Pageable pageable);


    @Query("SELECT kh FROM KhachHang kh WHERE kh.gioiTinh = :gender")
    Page<KhachHang> filterByGenderNoSearch(Pageable pageable,
                                      @Param("gender") boolean gender);

    @Query("Select kh from KhachHang kh WHERE (:keyword is null or  kh.sdt LIKE %:keyword% or kh.maKH like  %:keyword% or kh.hoTen like  %:keyword%or kh.email LIKE %:keyword%) and kh.gioiTinh = :gender")
    Page<KhachHang> fillterByGenderAnSearch(Pageable pageable, @Param("keyword") String keyword, @Param("gender") boolean gender);


    @Query("Select kh from KhachHang kh where kh.email = ?1")
    Optional<KhachHang> findByEmail(String email);

    @Query("Select kh from KhachHang kh where kh.sdt = ?1")
    Optional<KhachHang> findByPhone(String phone);

    @Query(value = "select top 1 k.id from KhachHang k order by k.id desc", nativeQuery = true)
    public Long findMaxId();
}
