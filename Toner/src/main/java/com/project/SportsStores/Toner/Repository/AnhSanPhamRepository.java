package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.AnhSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham,Long> {

    @Override
    void flush();

    @Override
    <S extends AnhSanPham> S saveAndFlush(S entity);

    @Override
    Optional<AnhSanPham> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    Page<AnhSanPham> findAll(Pageable pageable);

    @Query("select asp from AnhSanPham asp where asp.spct.id = :id")
    AnhSanPham getByIdProductDetail(@Param("id")String id);

    @Query("select asp from AnhSanPham asp inner join SanPhamChiTiet spct on asp.spct.id = spct.id where spct.sp.id = :id")
    List<AnhSanPham> getByIdProduct(@Param("id")String id);

    @Query("select asp from AnhSanPham asp inner join SanPhamChiTiet spct on asp.spct.id = spct.id where spct.sp.id = :id and spct.ms.id = :color")
    List<AnhSanPham> getByIdProductAndColor(@Param("id")String id,@Param("color")String color);

}
