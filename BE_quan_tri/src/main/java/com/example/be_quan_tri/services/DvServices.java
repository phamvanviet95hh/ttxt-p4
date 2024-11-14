package com.example.be_quan_tri.services;

import com.example.be_quan_tri.configSystem.SystemBe;
import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.GloableResponse;
import com.example.be_quan_tri.dtos.requests.RequestUpdateService;
import com.example.be_quan_tri.entitys.Services;
import com.example.be_quan_tri.repositorys.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class DvServices {

    private Boolean success;
    private String message;

    @Autowired
    private ServiceRepository serviceRepository;


    public Page<Services> getAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    public Page<CustomListDataServiceOfCategoryDto> getAllServiceOfCategory(Long idPartner, Long idCategory, Pageable pageable) {
        return serviceRepository.findById(idPartner, idCategory, pageable);
    }

    public Services getOneService(Long idService) {
        return serviceRepository.findById(idService).get();
    }

    public ResponseEntity<GloableResponse<RequestUpdateService>> updateService(RequestUpdateService requestUpdateService) {
        try {
            success = true;
            message = "Updated Successfully";
            Services service = getOneService(requestUpdateService.getId());
            BeanUtils.copyProperties(requestUpdateService, service, SystemBe.getNullPropertyNames(requestUpdateService));
            service.setUpdateAt(LocalDateTime.now());
            serviceRepository.save(service);
        }catch (Exception e) {
            success = false;
            message = "Updated False";
            e.printStackTrace();
        }
        return new ResponseEntity<>(
                new GloableResponse<>(success, message, null), HttpStatusCode.valueOf(HttpStatus.OK.value())
        );
    }

    public ResponseEntity<GloableResponse<RequestUpdateService>> addService(RequestUpdateService requestUpdateService) {
        Boolean service = serviceRepository.existsByServiceName(requestUpdateService.getServiceName());
        if (service) {
            success = false;
            message = "Service already exists";
        }else {
            try {
                Services services = Services.builder()
                        .serviceName(requestUpdateService.getServiceName())
                        .serviceCode(requestUpdateService.getServiceCode())
                        .servicePrice(requestUpdateService.getServicePrice())
                        .createAt(LocalDateTime.now())
                        .updateAt(LocalDateTime.now())
                        .build();
                serviceRepository.save(services);
                success = true;
                message = "Created Successfully";
            }catch (Exception e) {
                success = false;
                message = "Created False";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(
                new GloableResponse<>(success, message, null), HttpStatusCode.valueOf(HttpStatus.OK.value())
        );
    }

    public ResponseEntity<GloableResponse<RequestUpdateService>> deleteService(Long idService) {
        try{
            serviceRepository.deleteById(idService);
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
}
