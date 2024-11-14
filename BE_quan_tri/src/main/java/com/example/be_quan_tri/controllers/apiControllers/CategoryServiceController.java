package com.example.be_quan_tri.controllers.apiControllers;

import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.addServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.partners.CustomAddCateDtos;
import com.example.be_quan_tri.dtos.requests.RequestAddCategoryServiceDtos;
import com.example.be_quan_tri.entitys.*;
import com.example.be_quan_tri.repositorys.CategoryServicesRepository;
import com.example.be_quan_tri.repositorys.Category_to_DvRepository;
import com.example.be_quan_tri.repositorys.PartnerToCatetoServiceRepository;
import com.example.be_quan_tri.services.CategoryServiceDichVu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("category-service/")
public class CategoryServiceController {

    @Autowired
    private Category_to_DvRepository categoryToDvRepository;

    @Autowired
    private PartnerToCatetoServiceRepository partnerToCatetoServiceRepository;

    @Autowired
    private CategoryServiceDichVu categoryServiceDichVu;

    private Boolean success;
    private String message;

    @PostMapping("add")
    public ResponseEntity<GloableResponse<addServiceOfCategoryDto>> addServiceOfCategory(@RequestBody addServiceOfCategoryDto serviceOfCategoryDto){
        System.out.println(serviceOfCategoryDto);
        try {
            for (Long item : serviceOfCategoryDto.getData()){
                Partner_to_Service check = partnerToCatetoServiceRepository.findByPartnerAndServiceAndServiceChildren(new Partner(serviceOfCategoryDto.getIdPart()),new CategoryServices(serviceOfCategoryDto.getId()), new Services(item));
                if (check != null){
                    success = false;
                    message = "Đã tồn tại dịch vụ : " + check.getServiceChildren().getServiceName();
                }else {
                    Partner_to_Service partner_to_service = Partner_to_Service.builder()
                            .serviceChildren(new Services(item))
                            .service(new CategoryServices(serviceOfCategoryDto.getId()))
                            .partner(new Partner(serviceOfCategoryDto.getIdPart()))
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();
                    partnerToCatetoServiceRepository.save(partner_to_service);
                    success = true;
                    message = "Service added successfully";
                }

            }

        }catch (Exception e){

            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @DeleteMapping("delete")
    public ResponseEntity<GloableResponse<addServiceOfCategoryDto>> deleteServiceOfCategory(@RequestParam String idService,
                                                                                            @RequestParam String idCate,
                                                                                            @RequestParam String idPart){
        try {
            Long serviceid = Long.valueOf(idService);
            Long cateId = Long.valueOf(idCate);
            Long partId = Long.valueOf(idPart);
            Boolean check  = partnerToCatetoServiceRepository.existsByPartnerAndServiceAndServiceChildren(new Partner(partId), new CategoryServices(cateId), new Services(serviceid));
            if (check){
                partnerToCatetoServiceRepository.deleteByCustomDelete(partId, cateId, serviceid);
                success = true;
                message = "Service deleted successfully";
            }else {
                success = false;
                message = "This field does not exist";
            }

        }catch (Exception e){

            e.printStackTrace();
        }

        return new ResponseEntity<>(new GloableResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    @PostMapping("addCategoryServiceToPart")
    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> addCategoryServiceToPart(@RequestBody CustomAddCateDtos customAddCateDtos){
        return categoryServiceDichVu.addCategoryServiceToPart(customAddCateDtos);
    }

    @PostMapping("addCategoryService")
    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> addCategoryService(@RequestBody RequestAddCategoryServiceDtos requestAddCategoryServiceDtos){
        return categoryServiceDichVu.addCategoryService(requestAddCategoryServiceDtos);
    }

    @DeleteMapping("deleteCategoryService")
    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> deleteCategoryService(@RequestParam("id") String id){
        Long categoryId = Long.parseLong(id);
        return categoryServiceDichVu.deleteCategoryService(categoryId);
    }

    @DeleteMapping("deleteCategoryServiceToService")
    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> deleteCategoryServiceToService(@RequestParam("id") String id
    ){
        Long idDV = Long.parseLong(id);
        return categoryServiceDichVu.deleteCategoryServiceToService(idDV);
    }

    @GetMapping("updateStatusService")
    public ResponseEntity<GloableResponse<RequestAddCategoryServiceDtos>> updateStatusService(@RequestParam("id") String idCate, @RequestParam("status") String status){

        Long id = Long.valueOf(idCate);
        int st = Integer.parseInt(status);
        return categoryServiceDichVu.updateStatus(idCate, status);
    }

}
