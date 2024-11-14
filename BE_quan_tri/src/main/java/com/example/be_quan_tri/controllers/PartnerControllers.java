package com.example.be_quan_tri.controllers;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.UserRegisterDto;
import com.example.be_quan_tri.dtos.partners.RequestCheckPassword;
import com.example.be_quan_tri.services.PartnerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("admin_service/partner/")
public class PartnerControllers {

    @Autowired
    private PartnerService partnerService;

    @PostMapping(value = "add")
    public ResponseEntity<GloableResponse<UserRegisterDto>> register(@RequestParam("data") String data,
                                                                     @RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        return partnerService.registerUser(data, file);
    }
    @PostMapping(value = "checkPassword")
    public ResponseEntity<GloableResponse<RequestCheckPassword>> checkPassword(@RequestBody RequestCheckPassword checkPassword, HttpServletRequest request){
        return partnerService.checkPassword(checkPassword);
    }

    @DeleteMapping(value = "deletePatner")
    public ResponseEntity<GloableResponse<UserRegisterDto>> deletePatner(@RequestParam("id") String idPartner){
        Long idPartnerD = Long.valueOf(idPartner);
        return partnerService.deleteById(idPartnerD);
    }

    @DeleteMapping(value = "removeCateOfPassword")
    public ResponseEntity<GloableResponse<UserRegisterDto>> removeCateOfPassword(@RequestParam String idPart, @RequestParam String idCate){
        Long idPartner = Long.valueOf(idPart);
        Long idCategory = Long.valueOf(idCate);
        return partnerService.deleteByIdCreatedAt(idPartner, idCategory);
    }

}
