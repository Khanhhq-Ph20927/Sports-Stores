package com.project.SportsStores.Toner.validate;

import com.project.SportsStores.Toner.model.NhaCungCap;
import com.project.SportsStores.Toner.repository.NCCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NCCValidate implements Validator {

    @Autowired
    private NCCRepository nccRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NhaCungCap.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NhaCungCap ncc = (NhaCungCap) target;
        if(ncc.getId() != null){
            if (nccRepository.findByNameAndId(ncc.getName(), ncc.getId()).isPresent()) {
                errors.rejectValue("name", "error.name", "Tên nhà cung cấp này đã tồn tại");
            }
            if (nccRepository.findByNameAndId(ncc.getSDT(), ncc.getId()).isPresent()) {
                errors.rejectValue("SDT", "error.SDT", "SDT này đã tồn tại");
            }
            if (nccRepository.findByNameAndId(ncc.getEmail(), ncc.getId()).isPresent()) {
                errors.rejectValue("Email", "error.Email", "Email này đã tồn tại");
            }
        }
        else{
            if (nccRepository.findByName(ncc.getName()).isPresent()) {
                errors.rejectValue("name", "error.name", "Tên nhà cung cấp này đã tồn tại");
            }
            if (nccRepository.findByNameAndId(ncc.getSDT(), ncc.getId()).isPresent()) {
                errors.rejectValue("SDT", "error.SDT", "SDT này đã tồn tại");
            }
            if (nccRepository.findByNameAndId(ncc.getEmail(), ncc.getId()).isPresent()) {
                errors.rejectValue("Email", "error.Email", "Email này đã tồn tại");
            }
        }
    }
}
