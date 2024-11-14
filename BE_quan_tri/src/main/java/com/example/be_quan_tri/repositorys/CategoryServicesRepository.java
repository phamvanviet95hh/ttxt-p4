package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.partners.CustomCategoryDtos;
import com.example.be_quan_tri.dtos.services.CategoryServicesDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryServicesRepository extends JpaRepository<CategoryServices, Long> {

    @Query("select new com.example.be_quan_tri.dtos.partners.CustomCategoryDtos(pts.id as id, pts.partner.id as partnerId, pts.service.id as categoryServiceId, pts.service.serviceName as categoryServiceName) from Partner_to_Service pts where pts.partner.id = :idPartner")
    List<CustomCategoryDtos> findByIdAndCreatedAt(Long idPartner);


    @Query("select new com.example.be_quan_tri.dtos.services.CategoryServicesDtos(cs.id as id, cs.serviceName as categoryServiceName) from CategoryServices cs where cs.status = 1")
    List<CategoryServicesDtos> findByCreatedAt();
}
