package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {
    @Override
    Optional<ThuongHieu> findById(Long aLong);
}