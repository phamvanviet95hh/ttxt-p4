package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.UserRegisterDto;
import com.example.be_quan_tri.dtos.quotas.AddQuotasForPartnerDtos;
import com.example.be_quan_tri.dtos.quotas.QuotasRequestDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.entitys.Quotas;
import com.example.be_quan_tri.entitys.QuotasPartnerCategory;
import com.example.be_quan_tri.repositorys.QuotaRepository;
import com.example.be_quan_tri.repositorys.Quotas_to_PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuotaService {

    private Boolean success;
    private String message;

    @Autowired
    private QuotaRepository quotaRepository;

    @Autowired
    private Quotas_to_PartnerRepository quotasToPartnerRepository;

    public ResponseEntity<GloableResponse<QuotasRequestDtos>> registerUser(QuotasRequestDtos quotasRequestDtos) {

        try {
            Quotas quotas = Quotas.builder()
                    .quotaCode(quotasRequestDtos.getQuotaCode())
                    .quotas(quotasRequestDtos.getQuotas())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            quotaRepository.save(quotas);
            success = true;
            message = "Import Successful";
        }catch (Exception e) {
            success = false;
            message = "Import Failed";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(success, message, null),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<QuotasRequestDtos>> updateUser(QuotasRequestDtos quotasRequestDtos) {
        //
        return new ResponseEntity<>(new GloableResponse<>(success, message, null),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<AddQuotasForPartnerDtos>> addForPartner(AddQuotasForPartnerDtos addQuotasForPartnerDtos) {
        try {
            Quotas quotas = quotaRepository.getById(addQuotasForPartnerDtos.getIdQuota());
            QuotasPartnerCategory quotasPartnerCategory = QuotasPartnerCategory.builder()
                    .partner( new Partner(addQuotasForPartnerDtos.getIdPartner()))
                    .service( new CategoryServices(addQuotasForPartnerDtos.getIdCategory()))
                    .quotas( new Quotas(addQuotasForPartnerDtos.getIdQuota()))
                    .quotaRemain(quotas.getQuotas())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            quotasToPartnerRepository.save(quotasPartnerCategory);
            success = true;
            message = "Import Successful";
        }catch (Exception e) {
            success = false;
            message = "Import Fail";
            e.printStackTrace();
        }
        return new ResponseEntity<>(new GloableResponse<>(success, message, null),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
