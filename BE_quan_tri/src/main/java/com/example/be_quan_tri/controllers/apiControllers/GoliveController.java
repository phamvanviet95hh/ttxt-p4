package com.example.be_quan_tri.controllers.apiControllers;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.golives.RequestGoliveDtos;
import com.example.be_quan_tri.services.GoliveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("golive/")
public class GoliveController {

    @Autowired
    private GoliveService goliveService;


    @PostMapping("addTime")
    public ResponseEntity<GloableResponse<RequestGoliveDtos>> addTime(@RequestBody RequestGoliveDtos dtos) {
        return goliveService.addTime(dtos);
    }

}
