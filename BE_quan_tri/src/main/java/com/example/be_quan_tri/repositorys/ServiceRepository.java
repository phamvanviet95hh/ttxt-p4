package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.entitys.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface ServiceRepository extends JpaRepository<Services, Long> {


    @Query("select new com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto(ps.id, ps.service.id, ps.serviceChildren.id, ps.serviceChildren.serviceName, ps.serviceChildren.createAt) from Partner_to_Service ps where ps.service.id = :idCategory and ps.partner.id = :idPartner")
    Page<CustomListDataServiceOfCategoryDto> findById(Long idPartner, Long idCategory, Pageable pageable);

    Boolean existsByServiceName(String serviceName);
}
