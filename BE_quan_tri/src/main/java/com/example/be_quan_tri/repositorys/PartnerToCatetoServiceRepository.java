package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.entitys.Partner_to_Service;
import com.example.be_quan_tri.entitys.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface PartnerToCatetoServiceRepository extends JpaRepository<Partner_to_Service, Long> {
    Partner_to_Service findByPartnerAndServiceAndServiceChildren(Partner partner, CategoryServices categoryServices, Services services);

    Boolean existsByPartnerAndService(Partner partner, CategoryServices categoryServices);

    Boolean existsByPartnerAndServiceAndServiceChildren(Partner partner, CategoryServices categoryServices, Services services);

    @Modifying
    @Transactional
    @Query("delete from Partner_to_Service ps where ps.partner.id = :partId and ps.service.id = :cateId and (:serviceid is null or ps.serviceChildren.id = :serviceid)")
    void deleteByCustomDelete(Long partId, Long cateId, Long serviceid);

    List<Partner_to_Service> findByPartnerAndService(Partner partner, CategoryServices categoryServices);

    @Modifying
    @Transactional
    @Query("delete from Partner_to_Service ps where ps.partner.id = :partId and ps.service.id = :cateId")
    void deleteByCustomDeletePartner(Long partId, Long cateId);
}
