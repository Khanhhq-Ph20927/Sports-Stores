package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.ChucVu;

import java.util.List;
import java.util.Optional;

public interface ChucVuService {
    List<ChucVu> getAll();

    Optional<ChucVu> findById(Long aLong);
}