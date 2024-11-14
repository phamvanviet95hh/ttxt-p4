package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.golives.RequestGoliveDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Golive;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.repositorys.GoliveResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GoliveService {

    private Boolean success;
    private String message;


    @Autowired
    public GoliveResitory goliveResitory;

    public ResponseEntity<GloableResponse<RequestGoliveDtos>> addTime(RequestGoliveDtos dtos) {
        Golive goliveCheck = goliveResitory.findByPartner(dtos.getIdPart(), dtos.getIdCate());
        if (goliveCheck == null) {
            try {
                Golive golive = Golive.builder()
                        .categoryServices(new CategoryServices(dtos.getIdCate()))
                        .partner(new Partner(dtos.getIdPart()))
                        .dateGolive(dtos.getDatetime())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                goliveResitory.save(golive);
                success = true;
                message = "Add golive successfully";
            }catch (Exception e) {
                success = false;
                message = "Add golive FAILED";
                e.printStackTrace();
            }
        }else {
            success = false;
            message = "Golive time exists";
        }


        return new ResponseEntity<>(
                new GloableResponse<>(
                    success, message, null
                ), HttpStatusCode.valueOf(HttpStatus.OK.value())
        );
    }
}
