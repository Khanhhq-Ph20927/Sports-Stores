package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.TheLoai;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai,Long> {
    @Override
    List<TheLoai> findAll();

    @Override
    List<TheLoai> findAll(Sort sort);
}
