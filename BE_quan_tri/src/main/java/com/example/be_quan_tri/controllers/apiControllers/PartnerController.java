package com.example.be_quan_tri.controllers.apiControllers;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.GloableResponseList;
import com.example.be_quan_tri.dtos.ResponsePartner;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeAdmin;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeUser;
import com.example.be_quan_tri.dtos.partners.CustomAddCateDtos;
import com.example.be_quan_tri.dtos.requests.RequestUpdatePartnerDtos;
import com.example.be_quan_tri.services.PartnerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("partners/")
public class PartnerController {

    @Autowired
    private PartnerService partnerServices;

    private Boolean success = false;

    private String message = "";

    @GetMapping("checkUserName")
    public ResponseEntity<GloableResponse<ResponsePartner>> checkEmail(@RequestParam("username") String username){
        Boolean checkUserName = partnerServices.checkUserName(username);
        if(checkUserName){
            success = false;
            message = "User already exists";
        }else {
            success = true;
            message = "User does not exist yet";
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @GetMapping("checkCode")
    public ResponseEntity<GloableResponse<ResponsePartner>> checkCode(@RequestParam("code") String code){
        Boolean checkCode = partnerServices.checkCode(code);
        if(checkCode){
            success = false;
            message = "Code already exists";
        }else {
            success = true;
            message = "Code does not exist yet";
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PostMapping("updatePartner")
    public ResponseEntity<GloableResponse<ResponsePartner>> updatePartner( @RequestParam("data") String data,
                                                                           @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        System.out.println(data);
            return  partnerServices.updatePartner(data, file);
    }

    @PostMapping("addCategory")
    public ResponseEntity<GloableResponse<CustomAddCateDtos>> addCategory(@RequestBody CustomAddCateDtos customAddCateDtos){
        return  partnerServices.addCategory(customAddCateDtos);
    }

    @GetMapping("thongKe")
    public ResponseEntity<GloableResponseList<CustomThongKeAdmin>> thongKe(@RequestParam String idPart){
        Long idPartner = !Objects.equals(idPart, "") ? Long.valueOf(idPart) : null;
        return  partnerServices.thongKe(idPartner);
    }

    @GetMapping("thongKeUser")
    public ResponseEntity<GloableResponseList<CustomThongKeUser>> thongKeỦe(@RequestParam String idPart){
        Long idPartner = !Objects.equals(idPart, "") ? Long.valueOf(idPart) : null;
        return  partnerServices.thongKeUser(idPartner);
    }
}
