package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap,Long> {
    @Override
    Optional<NhaCungCap> findById(Long aLong);
}