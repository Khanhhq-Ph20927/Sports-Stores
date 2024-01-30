package com.project.SportsStores.Toner.validate;

import com.project.SportsStores.Toner.model.Voucher;
import com.project.SportsStores.Toner.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VoucherValidate implements Validator {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Voucher.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Voucher voucher = (Voucher) target;
        if(voucher.getEndDate().before(voucher.getStartDate())){
            errors.rejectValue("endDate", "error.endDate", "Ngày kết thúc không hợp lệ");
        }
        if(voucher.getId() != null){
            if (voucherRepository.findByNameAndId(voucher.getName(), voucher.getId()).isPresent()) {
                errors.rejectValue("name", "error.name", "Tên voucher này đã tồn tại");
            }
        }
        else{
            if (voucherRepository.findByName(voucher.getName()).isPresent()) {
                errors.rejectValue("name", "error.name", "Tên voucher này đã tồn tại");
            }
        }
    }
}
