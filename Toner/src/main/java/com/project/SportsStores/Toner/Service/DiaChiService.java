package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.DiaChi;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DiaChiService {

    List<DiaChi> findAll(Sort sort);

    List<DiaChi> getByIdKH(String id);

    void deleteById(String id);

    DiaChi getById(String id);
}
