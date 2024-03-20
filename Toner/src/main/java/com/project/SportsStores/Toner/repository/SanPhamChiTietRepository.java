package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Long> {

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
    Page<SanPhamChiTiet> findAll(Pageable pageable);

    @Override
    void deleteById(Long aLong);

    @Override
    List<SanPhamChiTiet> findAll(Sort sort);

    @Query("select spct from SanPhamChiTiet spct where spct.sp.id = :id")
    Page<SanPhamChiTiet> getSpctByIdSp(@Param("id") String id, Pageable pageable);

    @Query("select spct from SanPhamChiTiet spct where spct.sp.id = :id")
    List<SanPhamChiTiet> getListSpctByIdSp(@Param("id") String id);

    @Query("select s from SanPhamChiTiet s where s.sp.tenSP like ?1")
    Page<SanPhamChiTiet> search(String search, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.sp.tenSP like ?1 and s.ms.id = ?2 and s.size = ?3")
    Page<SanPhamChiTiet> searchAndFilterAll(String search, String ms, String size, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.sp.tenSP like ?1 and s.ms.id = ?2 ")
    Page<SanPhamChiTiet> searchAndFilterByColor(String search, String ms, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.sp.tenSP like ?1 and s.size = ?2 ")
    Page<SanPhamChiTiet> searchAndFilterBySize(String search, String size, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.size = ?1")
    Page<SanPhamChiTiet> FilterBySize(String size, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.ms.id = ?1")
    Page<SanPhamChiTiet> FilterByColor(String color, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.ms.id = ?1 and s.size = ?2 ")
    Page<SanPhamChiTiet> FilterByAll(String color, String size, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.size = ?1 and s.sp.id = ?2")
    Page<SanPhamChiTiet> FilterBySizeAndProduct(String size, String id, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.ms.id = ?1 and s.sp.id = ?2")
    Page<SanPhamChiTiet> FilterByColorAndProduct(String color, String id, Pageable pageable);

    @Query("select s from SanPhamChiTiet s where s.ms.id = ?1 and s.size = ?2 and s.sp.id = ?3")
    Page<SanPhamChiTiet> FilterByAllAndProduct(String color, String size, String id, Pageable pageable);

    //create by khanhhq
    @Query("select s from SanPhamChiTiet s where s.ms.id =:ms and s.size=:size")
    SanPhamChiTiet SearchADD(@Param("ms") String ms, @Param("size") String size);

    @Query("select s from SanPhamChiTiet s where s.sp.id=:id and s.ms.id =:ms ")
    List<SanPhamChiTiet> findListProductByColor(@Param("id") String id, @Param("ms") String ms);

    @Query("select s from SanPhamChiTiet s where s.sp.id=:id and s.ms.id =:ms and s.size =:size ")
    SanPhamChiTiet findIdProductByColorAndSize(@Param("id") String id, @Param("ms") String ms, @Param("size") String size);
}
