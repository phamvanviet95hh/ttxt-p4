package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.partners.CustomAddCateDtos;
import com.example.be_quan_tri.dtos.requests.RequestAddCategoryServiceDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.entitys.Partner_to_Service;
import com.example.be_quan_tri.entitys.Services;
import com.example.be_quan_tri.repositorys.CategoryServicesRepository;
import com.example.be_quan_tri.repositorys.Category_to_DvRepository;
import com.example.be_quan_tri.repositorys.PartnerToCatetoServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceDichVu {
    private Boolean success;
    private String message;

    @Autowired
    private CategoryServicesRepository categoryServicesRepository;

    @Autowired
    private PartnerToCatetoServiceRepository partnerToCatetoServiceRepository;

    @Autowired
    private Category_to_DvRepository categoryToDvRepository;

    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> addCategoryService(RequestAddCategoryServiceDtos requestAddCategoryServiceDtos) {
        try {

            CategoryServices categoryServices =CategoryServices.builder()
                    .serviceName(requestAddCategoryServiceDtos.getServiceName())
                    .serviceDetail(requestAddCategoryServiceDtos.getServiceDetail())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryServicesRepository.save(categoryServices);
            success = true;
            message = "Created CategoryService successfully";
        }catch (Exception e){
            success = false;
            message = "Created CategoryService fail";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> deleteCategoryService(Long categoryId) {
        try {
            categoryServicesRepository.deleteById(categoryId);
            success = true;
            message = "Delete CategoryService successfully";
        }catch (Exception e){
            success = false;
            message = "Delete CategoryService fail";
            e.printStackTrace();
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> deleteCategoryServiceToService(Long idDv) {
        try {
            categoryToDvRepository.deleteById(idDv);
            success = true;
            message = "Delete CategoryService successfully";
        }catch (Exception e){
            success = false;
            message = "Delete CategoryService fail";
            e.printStackTrace();
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> updateStatus(String idCate, String status) {
        try {
            CategoryServices categoryServices =categoryServicesRepository.findById(Long.parseLong(idCate)).get();
            categoryServices.setStatus(Integer.parseInt(status));
            categoryServicesRepository.save(categoryServices);
            success = true;
            message = "Updated CategoryService successfully";
        } catch (Exception e) {
            success = false;
            message = "Updated CategoryService Fail";
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> addCategoryServiceToPart(CustomAddCateDtos customAddCateDtos) {
        try{
            if (customAddCateDtos.getIdPartner() != null && !customAddCateDtos.getData().isEmpty()) {

                for (Long item : customAddCateDtos.getData()){
                    List<Partner_to_Service> partner = partnerToCatetoServiceRepository.findByPartnerAndService(new Partner(customAddCateDtos.getIdPartner()), new CategoryServices(item));
                    if (!partner.isEmpty()) {
                        success = false;
                        message = "Data already exists";
                    }else {
                        Partner_to_Service partner_to_service = Partner_to_Service.builder()
                                .service(new CategoryServices(item))
                                .partner(new Partner(customAddCateDtos.getIdPartner()))
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build();
                        partnerToCatetoServiceRepository.save(partner_to_service);
                        success = true;
                        message = "CategoryService added successfully";
                    }

                }
            }else {
                success = false;
                message = "CategoryService added fail";
            }

        } catch (Exception e) {
            success = false;
            message = "CategoryService added fail";
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
