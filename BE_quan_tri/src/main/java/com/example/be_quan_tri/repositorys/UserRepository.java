package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.dtos.dashboard.CustomThongKeAdmin;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeUser;
import com.example.be_quan_tri.dtos.dashboard.CustomTopPartner;
import com.example.be_quan_tri.dtos.partners.*;
import com.example.be_quan_tri.dtos.reports.ReportPartnerDtos;
import com.example.be_quan_tri.dtos.reports.ReportPartnerDtosService;
import com.example.be_quan_tri.dtos.reports.fomatReportServiceDtos;
import com.example.be_quan_tri.entitys.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Partner, Long> {

        Partner findFirstByUserName(String username);

        boolean existsByUserName(String userName);

        boolean existsByPartnerCode(String code);

        Page<Partner> findAll(Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomListDataServiceOfPartnerDto( pts.partner.id, pts.service.id, pts.partner.partnerCode, pts.service.serviceName, pts.serviceChildren.serviceName, pts.createdAt) from Partner_to_Service pts "
                        +
                        "where pts.partner.id = :idPartner")
        Page<CustomListDataServiceOfPartnerDto> findById(Long idPartner, Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomReportDto(pts.partner.id as idPartner, pts.partner.partnerName as partnerName, pts.partner.partnerCode as partnerCode, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalRequests , sum (CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) as totalSuccessfulRequests, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN pts.serviceChildren.servicePrice * r.totalRequestSuccess ELSE 0 END) as totalCost) from "
                        +
                        " Partner_to_Service pts " +
                        " left join Golive g on g.categoryServices.id = pts.service.id and g.partner.id = pts.partner.id"
                        +
                        " left join ReportDayEntity r on pts.partner.id = r.partner.id and r.service.id = pts.service.id"
                        +
                        " where EXTRACT(YEAR FROM r.createdAt) = 2024 and (g.dateGolive is null or r.createdAt >= g.dateGolive) and (pts.partner.id = :partnerId or :partnerId is null ) and pts.service.status = 1 and r.createdAt between :startDate and :endDate group by pts.partner.id, pts.partner.partnerName, pts.partner.partnerCode")
        Page<CustomReportDto> findByUpdatedAt(Long partnerId, LocalDateTime startDate, LocalDateTime endDate,
                        Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomReportDetailDtos(pts.serviceChildren.serviceName as serviceName, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalRequest, sum (CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) as totalSuccessfulRequests, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN pts.serviceChildren.servicePrice * r.totalRequestSuccess ELSE 0 END) AS totalCost) from "
                        +
                        " Partner_to_Service pts " +
                        " left join Golive g on pts.service.id = g.categoryServices.id and g.partner.id = pts.partner.id "
                        +
                        " left join ReportDayEntity r on pts.partner.id = r.partner.id and r.service.id = pts.service.id"
                        +
                        " where (pts.partner.id = :partnerId or :partnerId is null ) and pts.service.status = 1 and r.createdAt between :startDate and :endDate group by pts.serviceChildren.serviceName ")
        Page<CustomReportDetailDtos> findByCreatedAt(Long partnerId, LocalDateTime startDate, LocalDateTime endDate,
                        Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa(pts.partner.partnerCode as partnerCode, pts.service.serviceName as categoryName, pts.serviceChildren.serviceName as serviceName, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalRequest, sum(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalSuccessfulRequests, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * pts.serviceChildren.servicePrice ELSE 0 END) AS totalCost) from ReportDayEntity r"
                        +
                        " left join Partner_to_Service pts on pts.partner.id = r.partner.id and pts.service.id = r.service.id"
                        +
                        " left join Golive g on pts.service.id = g.categoryServices.id and g.partner.id = pts.partner.id"
                        +
                        " where (pts.partner.id = :partnerId or :partnerId is null ) and pts.service.status = 1 and r.createdAt between :startDate and :endDate group by pts.serviceChildren.serviceName, pts.partner.partnerCode , pts.service.serviceName order by pts.service.serviceName desc ")
        Page<CustomReportDetailDtosPa> findByCreatedAtAndPartnerAddress(Long partnerId, LocalDateTime startDate,
                        LocalDateTime endDate, Pageable pageable);

        @Query("select sum(qc.quotaRemain) from QuotasPartnerCategory qc where qc.partner.id =:idPartner")
        Long countByCreatedAt(Long idPartner);

        @Query("SELECT new com.example.be_quan_tri.dtos.reports.fomatReportServiceDtos(cs.id as id , cs.serviceName as cateServiceName, pts.serviceChildren.serviceName as serviceName, sum (r.totalRequestSuccess + r.totalRequestFailure) AS totalTransaction, sum(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalTransactionSuccess, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * pts.serviceChildren.servicePrice ELSE 0 END) AS totalMoney) "
                        +
                        " FROM ReportDayEntity r " +
                        " left JOIN Partner_to_Service pts ON r.service.id = pts.service.id and pts.partner.id = r.partner.id"
                        +
                        " left join CategoryServices  cs on cs.id = pts.service.id" +
                        " left join Golive g on r.service.id = g.categoryServices.id and g.partner.id = r.partner.id " +
                        " where (r.partner.id = :partnerId or :partnerId is null ) and cs.status = 1 and r.createdAt >= :timestampYesterday and r.createdAt <= :timestampToday  GROUP BY cs.id, pts.serviceChildren.serviceName , cs.serviceName order by cs.serviceName desc")
        List<fomatReportServiceDtos> findByTimeRequest(Long partnerId, LocalDateTime timestampYesterday,
                        LocalDateTime timestampToday);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomReportDetailDtos(pts.serviceChildren.id as idPartner, pts.serviceChildren.serviceName as serviceName, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalRequest, sum(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalSuccessfulRequests, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * pts.serviceChildren.servicePrice ELSE 0 END) AS totalCost) from "
                        +
                        " Partner_to_Service pts " +
                        " left join Golive g on pts.service.id = g.categoryServices.id and g.partner.id = pts.partner.id"
                        +
                        " left join ReportDayEntity r on r.partner.id = pts.partner.id and r.service.id = pts.service.id"
                        +
                        " where (pts.partner.id = :partnerId or :partnerId is null ) and pts.service.status = 1 and r.createdAt between :localDateTimeStart and :localDateTimeEnd group by pts.serviceChildren.serviceName, pts.serviceChildren.id")
        List<CustomReportDetailDtos> findByTimeRequestAndUpdatedAt(Long partnerId, LocalDateTime localDateTimeStart,
                        LocalDateTime localDateTimeEnd);

        @Query("select new com.example.be_quan_tri.dtos.reports.ReportPartnerDtos(r.partner.id as id, r.partner.partnerCode as partnerCode, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalTransaction, sum(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalTransactionSuccess) "
                        +
                        " from ReportDayEntity r " +
                        " left join Golive g on r.service.id = g.categoryServices.id and g.partner.id = r.partner.id " +
                        " where (r.partner.id = :partnerId or :partnerId is null ) " +
                        " and  r.service.id = :cateId " +
                        " and r.createdAt between :localDateTimeStart and :localDateTimeEnd " +
                        " group by r.partner.partnerCode, r.partner.id")
        List<ReportPartnerDtos> findByPartnerCode(Long partnerId, Long cateId, LocalDateTime localDateTimeStart,
                        LocalDateTime localDateTimeEnd);

        @Query("select new com.example.be_quan_tri.dtos.reports.ReportPartnerDtosService(r.partner.id as id, r.partner.partnerCode as partnerCode, sum (r.totalRequestSuccess + r.totalRequestFailure) as totalTransaction, sum(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess ELSE 0 END) AS totalTransactionSuccess, SUM(CASE WHEN  r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * pts.serviceChildren.servicePrice ELSE 0 END) AS totalMoney) "
                        +
                        " from ReportDayEntity r " +
                        " left join Partner_to_Service pts on r.service.id = pts.service.id and r.partner.id = pts.partner.id"
                        +
                        " left join Golive g on r.service.id = g.categoryServices.id and g.partner.id = r.partner.id" +
                        " where (r.partner.id = :partnerId or :partnerId is null ) and r.service.status = 1 and pts.serviceChildren.id = :cateId and r.createdAt between :localDateTimeStart and :localDateTimeEnd "
                        +
                        " group by r.partner.partnerCode, r.partner.id")
        List<ReportPartnerDtosService> findByPartnerCodeAndPartnerName(Long partnerId, Long cateId,
                        LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomCategoryOfPartnerDtos(s.id as idService, pts.id as idBox, s.serviceName as serviceName) "
                        +
                        " from Partner p" +
                        " join Partner_to_Service pts on pts.partner.id = p.id" +
                        " left join CategoryServices cs on cs.id = pts.service.id " +
                        " join Services s on s.id = pts.serviceChildren.id " +
                        " where pts.service.status = 1 and pts.partner.id = :idPartner and pts.service.id = :idCate" +
                        " group by s.serviceName, s.id, pts.id")
        List<CustomCategoryOfPartnerDtos> findByPartnerEmail(Long idPartner, Long idCate);

        @Query("select new com.example.be_quan_tri.dtos.dashboard.CustomTopPartner(pts.partner.id as partnerId, pts.partner.partnerName as partnerName, pts.partner.partnerCode as partnerCode, pts.partner.partnerUser as partnerUser, count(pts.service.id) as countCategory) "
                        +
                        " from Partner_to_Service pts group by pts.partner.id, pts.partner.partnerName, pts.partner.partnerCode, pts.partner.partnerUser order by countCategory DESC ")
        List<CustomTopPartner> findByPartnerAddress(Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa1(cs.id as categoryId, cs.serviceName as categoryName , g.dateGolive as dateGolive)"
                        +
                        " from CategoryServices cs" +
                        " left join Partner_to_Service pts on pts.service.id = cs.id" +
                        " left join Golive g on pts.service.id = g.categoryServices.id and g.partner.id = pts.partner.id"
                        +
                        " where (pts.partner.id = :idPartner or :idPartner is null ) and pts.service.status = 1  group by g.dateGolive, cs.id, cs.serviceName order by  cs.serviceName desc ")
        Page<CustomReportDetailDtosPa1> findByCreatedAtAndPartnerAddressAndUpdatedAt(Long idPartner, Pageable pageable);

        @Query("select new com.example.be_quan_tri.dtos.dashboard.CustomThongKeAdmin(EXTRACT(YEAR FROM r.createdAt) AS year, p.partnerCode as partnerCode, COALESCE(SUM(CASE WHEN g.dateGolive IS NOT NULL AND r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * s.servicePrice ELSE 0 END), 0) AS totalMoney) "
                        +
                        " from Partner p " +
                        " join Partner_to_Service ps on p.id = ps.partner.id" +
                        " left join CategoryServices cs on cs.id = ps.service.id" +
                        " left join Services s on s.id = ps.serviceChildren.id" +
                        " left join Golive g on g.categoryServices.id = cs.id and p.id = g.partner.id" +
                        " left join ReportDayEntity r on r.partner.id = p.id and r.service.id = cs.id " +
                        " where (:idPartner IS NULL OR p.id = :idPartner)" +
                        " group by extract(year from r.createdAt), p.partnerCode ")
        List<CustomThongKeAdmin> findByCreatedAtAndUpdatedAt(Long idPartner);

        @Query("select new  com.example.be_quan_tri.dtos.dashboard.CustomThongKeUser(m.month AS month, COALESCE(SUM(CASE WHEN g.dateGolive IS NOT NULL AND r.createdAt >= g.dateGolive THEN r.totalRequestSuccess * s.servicePrice ELSE 0 END), 0) AS totalMoney) " +
                " from Months m" +
                " cross join Partner p " +
                " left join Partner_to_Service ptc ON p.id = ptc.partner.id" +
                " left join CategoryServices cs ON ptc.service.id = cs.id" +
                " left join Services s on s.id = ptc.serviceChildren.id" +
                " left join Golive g on g.partner.id = p.id and g.categoryServices.id = cs.id" +
                " left join ReportDayEntity r on p.id = r.partner.id and cs.id = r.service.id" +
                " AND EXTRACT(MONTH FROM r.createdAt) = m.month" +
                " AND (g.dateGolive IS NULL OR r.createdAt >= g.dateGolive)" +
                " where (:idPartner IS NULL OR p.id = :idPartner) " +
                " group by m.month order by m.month asc")
        List<CustomThongKeUser> findByCreatedAtAndUpdatedAtForUser(Long idPartner);

        @Query("select new com.example.be_quan_tri.dtos.partners.CustomPartForTransaction(p.id as partId, p.partnerCode as partCode) from Partner p where (p.partnerCode like %:valuePartnerName% or :valuePartnerName is null)")
        List<CustomPartForTransaction> findByPartnerName(String valuePartnerName);
}
