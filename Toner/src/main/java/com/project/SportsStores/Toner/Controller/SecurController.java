package com.project.SportsStores.Toner.Controller;

import com.project.SportsStores.Toner.Model.KhachHang;
import com.project.SportsStores.Toner.Model.NhanVien;
import com.project.SportsStores.Toner.Repository.KhachHangRepository;
import com.project.SportsStores.Toner.Repository.NhanVienRepository;
import com.project.SportsStores.Toner.Service.MailService;
import com.project.SportsStores.Toner.Service.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/auth")
public class SecurController {

    @Autowired
    private MailService mailService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/init-reset-pass")
    public ResponseEntity<?> sendLinkResetPass(@RequestParam("email") String email) {
        System.out.println(email);
        Optional<KhachHang> khachHang = khachHangRepository.findByEmail(email);
        if(khachHang.isEmpty()){
            Optional<NhanVien> nhanVien = nhanVienRepository.getByEmail(email);
            if(nhanVien.isEmpty()){
                return new ResponseEntity<>("Không tìm thấy tài khoản", HttpStatus.EXPECTATION_FAILED);
            }
            mailService.sendEmail(email, "Quên mật khẩu","<h2>TONER</h2>:<br>" +
                    "Chúng tôi đã nhận được yêu cầu quên mật khẩu của bạn<br>Mật khẩu của bạn là:<br><br>" +
                    "<a style=\"background-color: #2f5fad; padding: 10px; color: #fff; font-size: 18px; font-weight: bold;\">"+nhanVien.get().getMatKhau()+"</a>",false, true);
        }
        else{
            mailService.sendEmail(email, "Quên mật khẩu","<h2>TONER</h2>:<br>" +
                    "Chúng tôi đã nhận được yêu cầu quên mật khẩu của bạn<br>Mật khẩu của bạn là:<br><br>" +
                    "<a style=\"background-color: #2f5fad; padding: 10px; color: #fff; font-size: 18px; font-weight: bold;\">"+khachHang.get().getMatKhau()+"</a>",false, true);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/regis-customer")
    public ResponseEntity<?> regisAccount(@RequestBody KhachHang khachHang){
        Optional<KhachHang> kh = khachHangRepository.findByEmail(khachHang.getEmail());
        if(kh.isPresent()){
            throw new MessageException("Email đã được sử dụng");
        }
        Optional<KhachHang> khphone = khachHangRepository.findByPhone(khachHang.getSdt());
        if(khphone.isPresent()){
            throw new MessageException("Số điện thoại đã được sử dụng");
        }
        khachHang.setMatKhauMaHoa(passwordEncoder.encode(khachHang.getMatKhau()));
        khachHang.setNgayTao(LocalDateTime.now());
        khachHang.setTrangThai(0);
        khachHang.setNgaySinh(new Date(System.currentTimeMillis()));
        khachHang.setLoaiKhachHang(0);
        khachHang.setGioiTinh(false);
        Long maxId = khachHangRepository.findMaxId();
        khachHang.setMaKH(String.valueOf("KH"+(maxId+1)));
        KhachHang result = khachHangRepository.save(khachHang);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}