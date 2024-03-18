package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan,Long> {
}
