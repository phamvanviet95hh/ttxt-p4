package com.example.be_quan_tri.services;

import com.example.be_quan_tri.configSystem.SystemBe;
import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.GloableResponseList;
import com.example.be_quan_tri.dtos.ResponsePartner;
import com.example.be_quan_tri.dtos.UserRegisterDto;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeAdmin;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeUser;
import com.example.be_quan_tri.dtos.dashboard.CustomTopPartner;
import com.example.be_quan_tri.dtos.partners.*;
import com.example.be_quan_tri.dtos.requests.RequestUpdatePartnerDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.entitys.Partner_to_Service;
import com.example.be_quan_tri.repositorys.PartnerToCatetoServiceRepository;
import com.example.be_quan_tri.repositorys.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class PartnerService {

    private Boolean success;
    private String message;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PartnerToCatetoServiceRepository partnerToCatetoServiceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GloableResponse<UserRegisterDto>> registerUser( String data, MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        UserRegisterDto userRegisterDto1 = objectMapper.readValue(data,
                UserRegisterDto.class);
        Partner register = userRepository.findFirstByUserName(userRegisterDto1.getUserName());

        if (register == null) {
            if (file.isEmpty()) {
                success = false;
                message = "Không có file";
                return new ResponseEntity<>(new GloableResponse<>(
                        success, message, null
                ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
            }
            byte[] bytes = file.getBytes();
            String base64Image= Base64.getEncoder().encodeToString(bytes);

            register = Partner.builder()
                    .partnerAddress(userRegisterDto1.getPartnerAddress())
                    .partnerPhone(userRegisterDto1.getPartnerPhone())
                    .partnerEmail(userRegisterDto1.getPartnerEmail())
                    .partnerName(userRegisterDto1.getPartnerName())
                    .partnerCode(userRegisterDto1.getPartnerCode())
                    .partnerUser(userRegisterDto1.getPartnerUser())
                    .partnerTax(userRegisterDto1.getPartnerTax())
                    .partnerDetail(userRegisterDto1.getPartnerDetail())
                    .updatedAt(LocalDateTime.now())
                    .userName(userRegisterDto1.getUserName())
                    .createdAt(LocalDateTime.now())
                    .goLiveDate(userRegisterDto1.getDateGoLive())
                    .password(passwordEncoder.encode(userRegisterDto1.getPassword()))
                    .role(userRegisterDto1.getRole())
                    .status(userRegisterDto1.getStatus())
                    .partnerName(userRegisterDto1.getPartnerName())
                    .partnerLogo(base64Image)
                    .build();
            Partner userEntity = userRepository.save(register);
            System.out.println(userEntity.getId());
            success = true;
            message = "Register Success !!!";
        }else {
            success = false;
            message = "Insert data Fail, User already exists!!!";
        }

        return new ResponseEntity<>(new GloableResponse<>(success, message, null),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public Boolean checkUserName(String username) {
        return userRepository.existsByUserName(username);
    }

    public Boolean checkCode(String code) {
        return userRepository.existsByPartnerCode(code);
    }

    public Page<Partner> getAllPartner(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Partner findById(Long idPartner) {
        return userRepository.findById(idPartner).get();
    }

    public ResponseEntity<GloableResponse<UserRegisterDto>> deleteById(Long idPartnerD) {
        try{
            userRepository.deleteById(idPartnerD);
            success = true;
            message = "Deleted Successfully";
        }catch (Exception e) {
            success = false;
            message = "Deleted False";
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                new GloableResponse<>(success, message, null), HttpStatusCode.valueOf(HttpStatus.OK.value())
        );
    }

    public ResponseEntity<GloableResponse<ResponsePartner>> updatePartner(String data, MultipartFile file) throws JsonProcessingException {

        ResponsePartner responsePartner = null;
        ObjectMapper objectMapper = new ObjectMapper();
        RequestUpdatePartnerDtos requestProductCategoryDto = objectMapper.readValue(data,
                RequestUpdatePartnerDtos.class);
        if (file.isEmpty()) {
            success = false;
            message = "Không có file";
            return new ResponseEntity<>(new GloableResponse<>(
                    success, message, null
            ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
        }
        try{
            byte[] bytes = file.getBytes();
            String base64Image= Base64.getEncoder().encodeToString(bytes);
            Partner partner = userRepository.findById(requestProductCategoryDto.getId()).get();

            BeanUtils.copyProperties(requestProductCategoryDto, partner, SystemBe.getNullPropertyNames(requestProductCategoryDto));
            partner.setUpdatedAt(LocalDateTime.now());
            if (base64Image != null) {
                partner.setPartnerLogo(base64Image);
            }
            if (requestProductCategoryDto.getPassword() != null) {
                partner.setPassword(passwordEncoder.encode(requestProductCategoryDto.getPassword()));
            }
            if (requestProductCategoryDto.getGoLiveDate() != null) {
                partner.setGoLiveDate(requestProductCategoryDto.getGoLiveDate());
            }
            userRepository.save(partner);
            responsePartner = ResponsePartner.builder()
                    .partnerLogo(base64Image)
                    .build();
            success = true;
            message = "Updated Successfully";
        }catch (Exception e) {
            success = false;
            message = "Updated False";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, responsePartner
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public Page<CustomListDataServiceOfPartnerDto> getAllServiceOfPartner(Long idPartner, Pageable pageable) {
      return   userRepository.findById(idPartner, pageable);
    }

    public List<Partner> findAll() {
        return userRepository.findAll();
    }

    public Long countQuota(Long idPartner) {
        return userRepository.countByCreatedAt(idPartner);
    }

    public List<CustomCategoryOfPartnerDtos> findByEmail(Long idPartner, Long idCate) {
        return userRepository.findByPartnerEmail(idPartner, idCate);
    }

    public Long countPartner() {
        return userRepository.count();
    }

    public List<CustomTopPartner> getTopPartner(Pageable pageable) {
        return userRepository.findByPartnerAddress(pageable);
    }

    public ResponseEntity<GloableResponse<CustomAddCateDtos>> addCategory(CustomAddCateDtos customAddCateDtos) {
        try{
            for (Long item : customAddCateDtos.getData()){
                Boolean partner_to_service = partnerToCatetoServiceRepository.existsByPartnerAndService(new Partner(customAddCateDtos.getIdPartner()), new CategoryServices(item));
                if (partner_to_service) {
                    success = false;
                    message = "Insert data Fail, Data already exists!!!";
                }else {
                    Partner_to_Service partnerToService = Partner_to_Service.builder()
                            .partner(new Partner(customAddCateDtos.getIdPartner()))
                            .service(new CategoryServices(item))
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();
                    partnerToCatetoServiceRepository.save(partnerToService);
                    success = true;
                    message = "Insert data Success!!!";
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<RequestCheckPassword>> checkPassword(RequestCheckPassword checkPassword) {
        try {
            Partner checkPartner = userRepository.findById(checkPassword.getPartnerId()).get();
            if (passwordEncoder.matches(checkPassword.getPassword(), checkPartner.getPassword())) {
                checkPartner.setPassword(passwordEncoder.encode(checkPassword.getNewPassword()));
                userRepository.save(checkPartner);
                success = true;
                message = "Change Password Success!!!";
            } else {
                success = false;
                message = "Change Password Fail!!!";
            }
        }catch (Exception e){
            success = false;
            message = "The account to change was not found.";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public List<CustomThongKeAdmin> loadThongKeAdmin(Long idPartner) {
        return userRepository.findByCreatedAtAndUpdatedAt(idPartner);
    }

    public ResponseEntity<GloableResponseList<CustomThongKeAdmin>> thongKe(Long idPartner) {
        List<CustomThongKeAdmin> thongKeAdmins = null;
        try {
            thongKeAdmins =userRepository.findByCreatedAtAndUpdatedAt(idPartner);
            success = true;
            message = "Get data success";
        }catch (Exception e){
            success = false;
            message = "Get data fail";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponseList<>(
                success, message, thongKeAdmins
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
    public ResponseEntity<GloableResponseList<CustomThongKeUser>> thongKeUser(Long idPartner) {
        List<CustomThongKeUser> thongKeUsers = null;
        try {
            thongKeUsers =userRepository.findByCreatedAtAndUpdatedAtForUser(idPartner);
            System.out.println(thongKeUsers);
            success = true;
            message = "Get data success";
        }catch (Exception e){
            success = false;
            message = "Get data fail";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponseList<>(
                success, message, thongKeUsers
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<UserRegisterDto>> deleteByIdCreatedAt(Long idPartner, Long idCategory) {
        List<Partner_to_Service> partner_to_service = partnerToCatetoServiceRepository.findByPartnerAndService(new Partner(idPartner), new CategoryServices(idCategory));
        if (partner_to_service.size() > 1){
            success = false;
            message = "Cần xóa hết dịch vụ có trong danh mục này trước";
        }else if(partner_to_service.isEmpty()) {
            success = false;
            message = "Không tồn tại trường dữ liệu này";
        } else {
            try{
                partnerToCatetoServiceRepository.deleteByCustomDeletePartner(idPartner, idCategory);
                success = true;
                message = "Xóa thành công";
            } catch (Exception e) {
                success = false;
                message = "Xóa thất bại";
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public List<CustomPartForTransaction> searchPartForTransaction(String valuePartnerName) {
        return userRepository.findByPartnerName(valuePartnerName);
    }
}
