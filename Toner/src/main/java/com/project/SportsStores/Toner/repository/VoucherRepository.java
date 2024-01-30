package com.project.SportsStores.Toner.repository;

import com.project.SportsStores.Toner.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

    @Query("select v from Voucher v where v.startDate >= ?1 and v.endDate <= ?2")
    public Page<Voucher> findByDate(Date start, Date end, Pageable pageable);

    @Query("select v from Voucher v where v.name = ?1 ")
    public Optional<Voucher> findByName(String name);

    @Query("select v from Voucher v where v.name = ?1 and v.id <> ?2")
    public Optional<Voucher> findByNameAndId(String code, Integer id);

}
