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

    // search query
    @Query("SELECT sp FROM SanPham sp WHERE sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%")
    Page<SanPham> searchProduct(@Param("keyword") String keyword, Pageable pageable);

    //search and filter by collection
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByCollection(@Param("keyword") String keyword, Pageable pageable,
                                                     @Param("collection") String collection);
    //filter by collection
    @Query("SELECT sp FROM SanPham sp WHERE" +
            " sp.danhMuc = :collection")
    Page<SanPham> filterProductByCollection(Pageable pageable,
                                            @Param("collection") String collection);

    //search and filter by status
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.trangThai = :status")
    Page<SanPham> searchAndFilterProductByStatus(@Param("keyword") String keyword, Pageable pageable,
                                                 @Param("status") String status);
    //filter by status
    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status")
    Page<SanPham> filterProductByStatus(Pageable pageable,
                                        @Param("status") String status);
    //filter by status and collection
    @Query("SELECT sp FROM SanPham sp WHERE " +
            " sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> filterProductByStatusAndCollection(Pageable pageable,
                                                     @Param("status") String status,
                                                     @Param("collection") String collection);
    // search and filter by status + collection
    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.trangThai = :status and sp.danhMuc = :collection")
    Page<SanPham> searchAndFilterProductByStatusAndCollection(Pageable pageable,
                                                              @Param("keyword") String keyword,
                                                              @Param("status") String status,
                                                              @Param("collection") String collection);

    @Query("SELECT sp FROM SanPham sp WHERE (sp.tenSP LIKE %:keyword% OR sp.maSP LIKE %:keyword%) " +
            "and sp.donGia <= :priceEnd and sp.donGia >= :priceStart")
    Page<SanPham> searchAndFilterProductByPrice(@Param("keyword") String keyword, Pageable pageable,
                                                @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

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
    //Query created by khanhhq
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1")
    Page<SanPham> pageClient(Pageable pageable);

    //price
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)")
    Page<SanPham> price(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd);

    //price+color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(spct.ms.id in :color)")
    Page<SanPham> priceAndFilterColor(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") List<Integer> color);

    //price+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(spct.size in :size)")
    Page<SanPham> priceAndFilterSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("size") List<String> size);

    //price+color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (sp.donGia <= :priceEnd and sp.donGia >= :priceStart)AND(spct.ms.id in :color)AND(spct.size in :size)")
    Page<SanPham> priceAndFilterColorAndSize(Pageable pageable, @Param("priceStart") String priceStart, @Param("priceEnd") String priceEnd, @Param("color") List<Integer> color, @Param("size") List<String> size);

    //color
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE spct.ms.id in :color")
    Page<SanPham> filterColor(Pageable pageable, @Param("color") List<Integer> color);

    //size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE spct.size in :size")
    Page<SanPham> filterSize(Pageable pageable, @Param("size") List<String> size);

    //color+size
    @Query("SELECT DISTINCT sp FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1 WHERE (spct.ms.id in :color) AND (spct.size in :size)")
    Page<SanPham> filterColorAndSize(Pageable pageable, @Param("color") List<Integer> color, @Param("size") List<String> size);

    //Price max
    @Query("Select MAX(sp.donGia) FROM SanPham sp INNER JOIN SanPhamChiTiet spct ON sp.id = spct.sp.id and sp.trangThai=1")
    Integer priceMax();

}
