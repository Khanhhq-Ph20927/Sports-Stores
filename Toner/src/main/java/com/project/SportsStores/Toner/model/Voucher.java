package com.project.SportsStores.Toner.model;

import com.project.SportsStores.Toner.enums.VoucherType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "KhuyenMai")
@Getter
@Setter
@ToString
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "NgayTao")
    private Timestamp createdDate;

    @Column(name = "TenKhuyenMai")
    private String name;

    @Column(name = "Banner")
    private String banner;

    @Column(name = "GiaTriGiam")
    private Float discount;

    @Column(name = "NgayBatDau")
    private Date startDate;

    @Column(name = "NgayKetThuc")
    private Date endDate;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "LoaiKhuyenMai")
    private Boolean voucherType;
}
