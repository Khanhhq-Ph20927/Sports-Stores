package com.project.SportsStores.Toner.Repository;

import com.project.SportsStores.Toner.Model.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<KhuyenMai, Long> {

    @Query("select v from KhuyenMai v where v.ngayBatDau >= ?1 and v.ngayKetThuc <= ?2")
    public Page<KhuyenMai> findByDate(Date start, Date end, Pageable pageable);

    @Query("select v from KhuyenMai v where v.tenKhuyenMai = ?1 ")
    public Optional<KhuyenMai> findByName(String name);

    @Query("select v from KhuyenMai v where v.tenKhuyenMai = ?1 and v.id <> ?2")
    public Optional<KhuyenMai> findByNameAndId(String code, Long id);

}
