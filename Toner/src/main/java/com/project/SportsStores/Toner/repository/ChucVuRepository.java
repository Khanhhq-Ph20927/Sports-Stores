package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu,Long> {
}