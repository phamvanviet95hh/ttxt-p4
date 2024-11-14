package com.example.be_quan_tri.controllers.apiControllers;


import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.UserRegisterDto;
import com.example.be_quan_tri.dtos.quotas.AddQuotasForPartnerDtos;
import com.example.be_quan_tri.dtos.quotas.QuotasRequestDtos;
import com.example.be_quan_tri.services.PartnerService;
import com.example.be_quan_tri.services.QuotaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/report/v1")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    @PostMapping(value = "add")
    public ResponseEntity<GloableResponse<QuotasRequestDtos>> register(@RequestBody QuotasRequestDtos quotasRequestDtos, HttpServletRequest request){
        return quotaService.registerUser(quotasRequestDtos);
    }

    @PostMapping(value = "update")
    public ResponseEntity<GloableResponse<QuotasRequestDtos>> update(@RequestBody QuotasRequestDtos quotasRequestDtos, HttpServletRequest request){
        return quotaService.updateUser(quotasRequestDtos);
    }

    @PostMapping(value = "addForPartner")
    public ResponseEntity<GloableResponse<AddQuotasForPartnerDtos>> addForPartner(@RequestBody AddQuotasForPartnerDtos addQuotasForPartnerDtos, HttpServletRequest request){
        return quotaService.addForPartner(addQuotasForPartnerDtos);
    }

}
