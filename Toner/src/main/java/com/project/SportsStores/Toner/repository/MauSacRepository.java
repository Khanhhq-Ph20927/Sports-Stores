package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
    @Override
    List<MauSac> findAll();

    @Override
    <S extends MauSac> S save(S entity);

    @Override
    Optional<MauSac> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    List<MauSac> findAll(Sort sort);

    @Override
    Page<MauSac> findAll(Pageable pageable);
}
