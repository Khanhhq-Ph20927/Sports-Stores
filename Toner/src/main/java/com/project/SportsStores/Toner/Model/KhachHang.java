package com.project.SportsStores.Toner.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="KhachHang")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class KhachHang implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MaKH",unique = true)
    private String maKH;

    @Column(name="HoTen")
    private String hoTen;

    @Column(name = "SDT",unique = true)
    private String sdt;

    @Column(name="NgaySinh")
    private Date ngaySinh;

    @Column(name="TrangThai")
    private int trangThai;

    @Column(name="Email",unique = true)
    private String email;

    @Column(name="MatKhau")
    private String matKhau;

    @Column(name="MatKhauMaHoa")
    private String matKhauMaHoa;

    @Column(name="NgayTao")
    private LocalDateTime ngayTao;

    @Column(name="AnhKhachHang")
    private String anhKhachHang;

    @Column(name="LoaiKhachHang")
    private int loaiKhachHang;

    @Column(name="GioiTinh")
    private boolean gioiTinh;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("CUSTOMER"));
    }

    @Override
    public String getPassword() {
        return matKhauMaHoa;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
