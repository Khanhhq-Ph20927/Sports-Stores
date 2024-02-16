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
}
