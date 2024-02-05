package com.project.SportsStores.Toner.service;

import com.project.SportsStores.Toner.model.NhaCungCap;
import com.project.SportsStores.Toner.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public interface NhaCungCapService {

    public NhaCungCap create(NhaCungCap ncc);
    public void delete(Integer id);
    public Optional<NhaCungCap> findById(Integer id);

}