package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.ThongTinVanChuyen;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThongTinVanChuyenRepository extends JpaRepository<ThongTinVanChuyen,Long> {


    @Override
    <S extends ThongTinVanChuyen> S saveAndFlush(S entity);

    @Override
    List<ThongTinVanChuyen> findAll();

    @Override
    <S extends ThongTinVanChuyen> S save(S entity);

    @Override
    Optional<ThongTinVanChuyen> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<ThongTinVanChuyen> findAll(Sort sort);

}
