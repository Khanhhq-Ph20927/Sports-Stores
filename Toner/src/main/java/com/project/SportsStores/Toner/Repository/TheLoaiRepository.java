package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.TheLoai;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai,Long> {
    @Override
    List<TheLoai> findAll();

    @Override
    List<TheLoai> findAll(Sort sort);

    @Query("select t from TheLoai t where t.ten = ?1 and t.id <> ?2")
    Optional<TheLoai> findByNameAndId(String ten, Long id);

    @Query("select t from TheLoai t where t.ten = ?1")
    Optional<Object> findByName(String ten);
}
