package com.project.SportsStores.Toner.repository;

import com.project.SportsStores.Toner.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NCCRepository extends JpaRepository<NhaCungCap, Integer> {

    @Query("select v from NhaCungCap v where v.name = ?1 ")
    public Optional<NhaCungCap> findByName(String name);

    @Query("select v from NhaCungCap v where v.name = ?1 and v.id <> ?2")
    public Optional<NhaCungCap> findByNameAndId(String code, Integer id);

}
