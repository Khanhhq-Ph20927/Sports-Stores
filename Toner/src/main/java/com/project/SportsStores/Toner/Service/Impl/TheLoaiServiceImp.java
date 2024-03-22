package com.project.SportsStores.Toner.Service.Impl;

import com.project.SportsStores.Toner.Model.TheLoai;
import com.project.SportsStores.Toner.Repository.TheLoaiRepository;
import com.project.SportsStores.Toner.Service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TheLoaiServiceImp implements TheLoaiService {

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Override
    public TheLoai create(TheLoai theLoai) {
        return theLoaiRepository.save(theLoai);
    }

    @Override
    public void delete(Long id) {
        theLoaiRepository.deleteById(id);
    }

    @Override
    public Page<TheLoai> findAll(Pageable pageable) {
        return theLoaiRepository.findAll(pageable);
    }

    @Override
    public Optional<TheLoai> findById(Long id) {
        return theLoaiRepository.findById(id);
    }
}
