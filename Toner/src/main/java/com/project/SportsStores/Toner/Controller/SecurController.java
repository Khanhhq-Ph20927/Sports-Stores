package com.project.SportsStores.Toner.Controller;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SecurController {

    @Autowired
    private MailService mailService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @PostMapping("/init-reset-pass")
    public ResponseEntity<?> sendLinkResetPass(@RequestParam("email") String email) {
        System.out.println(email);
        Optional<KhachHang> khachHang = khachHangRepository.findByEmail(email);
        if(khachHang.isEmpty()){
            Optional<NhanVien> nhanVien = nhanVienRepository.getByEmail(email);
            if(nhanVien.isEmpty()){
                return new ResponseEntity<>("Không tìm thấy tài khoản", HttpStatus.EXPECTATION_FAILED);
            }
            mailService.sendEmail(email, "Quên mật khẩu","Cảm ơn bạn đã tin tưởng và xử dụng dịch vụ của chúng tôi:<br>" +
                    "Chúng tôi đã nhận được yêu cầu quên mật khẩu của bạn<br><br>" +
                    "<a style=\"background-color: #2f5fad; padding: 10px; color: #fff; font-size: 18px; font-weight: bold;\">"+nhanVien.get().getMatKhau()+"</a>",false, true);
        }
        else{
            mailService.sendEmail(email, "Quên mật khẩu","Cảm ơn bạn đã tin tưởng và xử dụng dịch vụ của chúng tôi:<br>" +
                    "Chúng tôi đã nhận được yêu cầu quên mật khẩu của bạn<br><br>" +
                    "<a style=\"background-color: #2f5fad; padding: 10px; color: #fff; font-size: 18px; font-weight: bold;\">"+khachHang.get().getMatKhau()+"</a>",false, true);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
