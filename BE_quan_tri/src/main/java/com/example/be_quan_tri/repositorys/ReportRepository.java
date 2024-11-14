package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.dtos.reports.ReportCategoryOfPartner;
import com.example.be_quan_tri.entitys.ReportHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReportRepository extends JpaRepository<ReportHistory, Long> {

    @Query("select new com.example.be_quan_tri.dtos.reports.ReportCategoryOfPartner(cs.id as idCate, cs.serviceName as categoryName, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalRequest, sum (CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalTransactionSuccess) " +
            " from ReportDayEntity r " +
            " left join CategoryServices cs on cs.id = r.service.id" +
            " left join Golive g on r.service.id = g.categoryServices.id and g.partner.id = r.partner.id " +
            " where r.partner.id = :partnerId and cs.status = 1 and r.createdAt between :localDateTimeStart and :localDateTimeEnd" +
            " group by cs.id, cs.serviceName")
    Page<ReportCategoryOfPartner> findByCreatedAt(Long partnerId, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd, Pageable pageable);
}
