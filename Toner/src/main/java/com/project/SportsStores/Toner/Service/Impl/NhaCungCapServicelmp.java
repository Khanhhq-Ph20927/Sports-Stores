package com.project.SportsStores.Toner.service.impl;

import com.project.SportsStores.Toner.model.NhaCungCap;
import com.project.SportsStores.Toner.model.Voucher;
import com.project.SportsStores.Toner.repository.NCCRepository;
import com.project.SportsStores.Toner.repository.VoucherRepository;
import com.project.SportsStores.Toner.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NhaCungCapServicelmp implements NhaCungCapService {

    @Autowired
    private NCCRepository nccRepository;

    @Override
    public NhaCungCap create(NhaCungCap ncc) {
        NhaCungCap result = nccRepository.save(ncc);
        return result;
    }

    @Override
    public void delete(Integer id) {
        nccRepository.deleteById(id);
    }


    @Override
    public Optional<NhaCungCap> findById(Integer id) {
        Optional<NhaCungCap> ex = nccRepository.findById(id);
        return ex;
    }
}
