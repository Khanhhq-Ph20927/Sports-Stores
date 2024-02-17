package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.ChucVu;
import com.project.SportsStores.Toner.Repository.ChucVuRepository;
import com.project.SportsStores.Toner.Service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    ChucVuRepository repository;
    @Override
    public List<ChucVu> getAll() {
        return repository.findAll();
    }
}
