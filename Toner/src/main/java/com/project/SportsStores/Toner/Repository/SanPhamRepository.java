package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Override
    void flush();

    @Override
    <S extends SanPham> S saveAndFlush(S entity);

    @Override
    List<SanPham> findAll();

    @Override
    <S extends SanPham> S save(S entity);

    @Override
    Optional<SanPham> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Page<SanPham> findAll(Pageable pageable);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%")
    Page<SanPham> searchProduct(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByCollection(@Param("keyword") String keyword, Pageable pageable,
                                                     @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE" +
            " sp.danhMuc = :collection")
    Page<SanPham> filterProductByCollection(Pageable pageable,
                                            @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.trangThai = :status")
    Page<SanPham> searchAndFilterProductStatus(@Param("keyword") String keyword, Pageable pageable,
                                               @Param("status") String status);

    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status")
    Page<SanPham> filterProductStatus(Pageable pageable,
                                      @Param("status") String status);

    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> filterProductStatusAndCollection(Pageable pageable,
                                                   @Param("status") String status, @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart")
    Page<SanPham> searchAndFilterProductByPrice(@Param("keyword") String keyword, Pageable pageable,
                                                @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByStatusAndCollection(@Param("keyword") String keyword, Pageable pageable,
                                                              @Param("status") String status, @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.trangThai = :status and sp.donGia <= :priceEnd and sp.donGia >= :priceStart")
    Page<SanPham> searchAndFilterProductByStatusAndPrice(@Param("keyword") String keyword, Pageable pageable,
                                                         @Param("status") String status,
                                                         @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByCollectionAndPrice(@Param("keyword") String keyword, Pageable pageable,
                                                             @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd,
                                                             @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword% " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart and sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByAll(@Param("keyword") String keyword, Pageable pageable,
                                              @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd,
                                              @Param("status") String status, @Param("collection") String collection);


}
