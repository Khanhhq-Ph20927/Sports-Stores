package com.project.SportsStores.Toner.Controller.admin;

import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTO;
import com.project.SportsStores.Toner.Model.DTO.SanPhamChiTietDTOValidation;
import com.project.SportsStores.Toner.Model.DTO.SanPhamDTO;
import com.project.SportsStores.Toner.Model.CustomModel.ResponseCustom;
import com.project.SportsStores.Toner.Model.SanPham;
import com.project.SportsStores.Toner.Model.SanPhamChiTiet;
import com.project.SportsStores.Toner.Repository.NhaCungCapRepository;
import com.project.SportsStores.Toner.Repository.ThuongHieuRepository;
import com.project.SportsStores.Toner.Service.Impl.MauSacServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamChiTietServiceImpl;
import com.project.SportsStores.Toner.Service.Impl.SanPhamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin/product")
public class RestControllerSanPham {

    @Autowired
    private SanPhamServiceImpl sv;

    @Autowired
    private SanPhamChiTietServiceImpl sv2;

    @Autowired
    private MauSacServiceImpl sv3;

    @Autowired
    private NhaCungCapRepository rp1;

    @Autowired
    private ThuongHieuRepository rp2;

    @RequestMapping(value = "/page/search/{pageNumber}/{keyWord}", method = RequestMethod.GET)
    private ResponseEntity<?> pagination(@PathVariable("pageNumber") int pageNumber,
                                         @PathVariable("keyWord") String keyWord) {
        Pageable pageable = PageRequest.of(pageNumber, 5, Sort.by("ngayTao").descending());
        if (keyWord.equalsIgnoreCase("null")) {
            Page<SanPham> page = sv.getPagination(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            Page<SanPham> page = sv.seacrhProduct(keyWord, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/product_detail/{id}/{pageNumber}", method = RequestMethod.GET)
    private ResponseEntity<?> getProductDetail(@PathVariable("id") String id,
                                               @PathVariable("pageNumber") int pageNumber,
                                               @RequestParam(value = "color", required = false) String color,
                                               @RequestParam(value = "size", required = false) String size) {
        Page<SanPhamChiTiet> page = sv2.Filter(pageNumber,color,size, id);
        return ResponseEntity.ok().body(page);
    }

    @RequestMapping(value = "/page/{keyWord}/{pageNumber}/{status}/{collection}", method = RequestMethod.GET)
    private ResponseEntity<?> paginationAndFilter(@PathVariable("keyWord") String keyWord,
                                                  @PathVariable("pageNumber") String pageNumber,
                                                  @PathVariable("status") String status,
                                                  @PathVariable("collection") String collection) {
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), 5, Sort.by("ngayTao").descending());
        Page<SanPham> page = sv.getPagination(pageable);
        //search
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) == -1 && !keyWord.equals("null")) {
            page = sv.seacrhProduct(keyWord, pageable);
        }
        //filter by status
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) == -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusNoSearch(pageable, status);
        }
        //filter by collection
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByCollectionNoSearch(pageable, collection);
        }
        //filter by status and collection
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) != -1 && keyWord.equalsIgnoreCase("null")) {
            page = sv.filterByStatusAndCollectionNoSearch(pageable, status, collection);
        }
        //search and filter by status
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) == -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByStatus(keyWord, pageable, status);
        }
        //search and filter by collection
        if (Integer.parseInt(status) == -1 && Integer.parseInt(collection) != -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByCollection(keyWord, pageable, collection);
        }
        //search and filter by status + collection
        if (Integer.parseInt(status) != -1 && Integer.parseInt(collection) != -1 && !keyWord.equalsIgnoreCase("null")) {
            page = sv.searchAndfilterByStatusAndCollection(keyWord, pageable, status, collection);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_status/{id}/{status}", method = RequestMethod.GET)
    private ResponseEntity<?> updateStatus(@PathVariable("id") String id,
                                           @PathVariable("status") String status) {
        boolean isExist = sv.getById(Long.parseLong(id)).isPresent();
        if (isExist == true) {
            SanPham sp = sv.getById(Long.parseLong(id)).get();
            sp.setTrangThai(Integer.parseInt(status));
            sv.update(sp);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/save_product_detail/validation/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> validationProductDetail(@RequestBody() List<SanPhamChiTietDTO> listSPCT
            , @PathVariable("id") String id) {
        List<SanPhamChiTietDTOValidation> arrayProductValidation = new ArrayList<>();
        if (sv.getById(Long.valueOf(id)).isPresent()) {
            for (SanPhamChiTietDTO dto : listSPCT) {
                //Tạo 1 oject để gán dữ liệu lấy được từ request và tiến hành validate
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                SanPham sp = sv.getById(Long.valueOf(id)).get();
                sanPhamChiTiet.setMs(sv3.getById(Integer.parseInt(dto.getMs())));
                sanPhamChiTiet.setSize(dto.getSize().toUpperCase(Locale.ROOT));
                sanPhamChiTiet.setSoLuong(Integer.parseInt(dto.getSl()));
                sanPhamChiTiet.setSp(sp);
                sanPhamChiTiet.setNgayTao(LocalDateTime.now());
                //Tạo 1 object để trả về lỗi cho giao diện xử lý
                SanPhamChiTietDTOValidation objectValidation = new SanPhamChiTietDTOValidation();
                objectValidation.setMs(dto.getMs());
                objectValidation.setSize(dto.getSize());
                objectValidation.setSl(dto.getSl());

                for (SanPhamChiTiet spctLoopFor : sv2.getListByIdSp(id)
                ) {
                    String sizeTemp = sanPhamChiTiet.getSize();
                    String size = spctLoopFor.getSize();
                    boolean isValidColor = spctLoopFor.getMs().getId() == sanPhamChiTiet.getMs().getId();
                    boolean isValidSize = sizeTemp.equals(size);
                    if (isValidSize && isValidColor) {
                        objectValidation.setValid(true);
                        arrayProductValidation.add(objectValidation);
                        break;
                    } else {
                        objectValidation.setValid(false);
                        arrayProductValidation.add(objectValidation);
                    }
                }
            }

        } else {
            return ResponseEntity.ok("failure");
        }
        List<SanPhamChiTietDTOValidation> arrayProductValidationWithOutDuplicate =
                arrayProductValidation.stream().distinct().collect(Collectors.toList());
        System.out.println(arrayProductValidationWithOutDuplicate.toString());
        return ResponseEntity.ok(arrayProductValidationWithOutDuplicate);
    }

    @RequestMapping(value = "/save_product_detail/save/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> saveProductDetail(@RequestBody() List<SanPhamChiTietDTOValidation> listSPCT
            , @PathVariable("id") String id) {
        if (sv.getById(Long.valueOf(id)).isPresent()) {
            for (SanPhamChiTietDTOValidation dto : listSPCT) {
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                SanPham sp = sv.getById(Long.valueOf(id)).get();
                sanPhamChiTiet.setMs(sv3.getById(Integer.parseInt(dto.getMs())));
                sanPhamChiTiet.setSize(dto.getSize().toUpperCase(Locale.ROOT));
                sanPhamChiTiet.setSoLuong(Integer.parseInt(dto.getSl()));
                sanPhamChiTiet.setSp(sp);
                sanPhamChiTiet.setNgayTao(LocalDateTime.now());
                sv2.save(sanPhamChiTiet);
            }
        }
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.POST)
    private ResponseEntity<?> saveProduct(@RequestBody SanPhamDTO dto) {

        List<ResponseCustom> listResponse = new ArrayList<>();

        // Regular expression
        String regexPrice = "^(?=.*[1-9])\\d+$";

        String regexName = "^[a-zA-ZÀ-Ỹà-ỹ][a-zA-Z0-9À-Ỹà-ỹ ]{9,50}$";

        System.out.println(dto.toString());

        Pattern pattern = Pattern.compile(regexPrice);
        Matcher matcher = pattern.matcher(String.valueOf(dto.getDonGia()));

        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherName = patternName.matcher(dto.getTenSP());

        SanPham sp = new SanPham();

        List<SanPham> list = sv.getAll();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int index = Integer.parseInt(list.get(i).getMaSP().substring(2));
            integerList.add(index);
        }
        Optional<Integer> maxNumber = integerList.stream().max(Integer::compareTo);
        maxNumber.ifPresent(integer -> sp.setMaSP("SP" + (integer + 1)));
        boolean isValid = true;
        sp.setNgayTao(LocalDateTime.now());
        if (dto.getDanhMuc() == -1) {
            isValid = false;
        }
        if (dto.getTenSP().isEmpty()) {
            isValid = false;
        }
        if (!matcherName.matches()) {
            isValid = false;
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("errorFormatName");
            listResponse.add(responseCustom);
        }
        if (dto.getThieu() == null || rp2.findById(dto.getThieu()).isEmpty()) {
            isValid = false;
        }
        if (dto.getNcc() == null || rp1.findById(dto.getNcc()).isEmpty()) {
            isValid = false;
        }
        if (dto.getDonGia() == null) {
            isValid = false;
        }
        if (dto.getDonGia() == null) {
            isValid = false;

        }
        if (matcher.matches()) {
            if (BigDecimal.valueOf(Long.parseLong(dto.getDonGia())).compareTo(new BigDecimal("70000")) < 0) {
                isValid = false;
                ResponseCustom responseCustom = new ResponseCustom();
                responseCustom.setStatusText("failure");
                responseCustom.setMessage("errorPriceLessThan");
                listResponse.add(responseCustom);
            }
            if (BigDecimal.valueOf(Long.parseLong(dto.getDonGia())).compareTo(new BigDecimal("5000000")) > 0) {
                isValid = false;
                ResponseCustom responseCustom = new ResponseCustom();
                responseCustom.setStatusText("failure");
                responseCustom.setMessage("errorPriceMoreThan");
                listResponse.add(responseCustom);
            }
        } else {
            isValid = false;
            ResponseCustom responseCustom = new ResponseCustom();
            responseCustom.setStatusText("failure");
            responseCustom.setMessage("errorFormatPrice");
            listResponse.add(responseCustom);
        }
        if (isValid) {
            sp.setTenSP(dto.getTenSP());
            sp.setDanhMuc(dto.getDanhMuc());
            sp.setTrangThai(dto.getTrangThai());
            sp.setDonGia(BigDecimal.valueOf(Long.parseLong(dto.getDonGia())));
            sp.setThieu(rp2.findById(dto.getThieu()).get());
            sp.setNcc(rp1.findById(dto.getNcc()).get());
            sv.save(sp);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok(listResponse);
        }
    }


}
