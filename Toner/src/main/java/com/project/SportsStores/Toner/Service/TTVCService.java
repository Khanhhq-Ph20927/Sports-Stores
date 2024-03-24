package com.project.SportsStores.Toner.Service;

import com.project.SportsStores.Toner.Model.ThongTinVanChuyen;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TTVCService {

    void save (ThongTinVanChuyen ttvc);

    List<ThongTinVanChuyen> findAll(Sort sort);

    void deleteById(String id);

    ThongTinVanChuyen findById(String id);
}
