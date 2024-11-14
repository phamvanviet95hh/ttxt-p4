package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.dtos.dashboard.CustomCount;
import com.example.be_quan_tri.dtos.transactions.CustomTransactionInfo;
import com.example.be_quan_tri.entitys.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query("SELECT t FROM Transactions t WHERE (:partnerId IS NULL OR t.partner.id = :partnerId) and (:partnerCode IS NULL OR t.partner.partnerCode = :partnerCode) AND (:status IS NULL OR t.statusCode = :status) AND  t.timeRequest BETWEEN :start AND :end order by t.id desc")
    Page<Transactions> findByTimeRequest(Long partnerId, String partnerCode, String status, LocalDateTime start, LocalDateTime end, Pageable pageable);


    @Query("SELECT t FROM Transactions t WHERE (:partnerId IS NULL OR t.partner.id = :partnerId) and (:partnerCodeTran IS NULL OR t.partner.partnerCode = :partnerCodeTran) AND (:statusTran IS NULL OR t.statusCode = :statusTran) AND  t.timeRequest BETWEEN :localDateTimeStart AND :localDateTimeEnd order by t.id desc")
    List<Transactions> findByTimeRequestAndApiKey(Long partnerId, String partnerCodeTran, String statusTran, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    @Query("select COALESCE(SUM(r.totalRequestSuccess), 0) AS totalTransactionSuccess  from ReportDayEntity r " +
            " join CategoryServices cs on cs.id = r.service.id " +
            " left join Golive g on g.partner.id = r.partner.id and g.categoryServices.id = r.service.id" +
            " where (r.partner.id = :idPartner or :idPartner is null ) and r.createdAt between :localDateTimeStart and :localDateTimeEnd")
    Long findByIdAndCreatedAt(Long idPartner, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    @Query("select COALESCE(SUM(r.totalRequestSuccess * s.servicePrice), 0) AS monthlyCost from ReportDayEntity r" +
            " join Partner_to_Service ps on ps.partner.id = r.partner.id and ps.service.id = r.service.id" +
            " left join CategoryServices cs on cs.id = ps.service.id" +
            " left join Services s on s.id = ps.serviceChildren.id" +
            " left join Golive g on g.categoryServices.id = cs.id" +
            " where (r.partner.id = :idPartner or :idPartner is null ) and  (g.dateGolive IS NULL OR r.createdAt >= g.dateGolive) and r.createdAt between :localDateTimeStart and :localDateTimeEnd")
    Long findByIdAndUpatedAt(Long idPartner, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    @Query("select new com.example.be_quan_tri.dtos.transactions.CustomTransactionInfo(t.path as path, t.orgCode as orgCode, t.idQ as idQ, t.statusCode as statusCode, t.requestId as requestId, t.serviceCodeRequest as serviceCodeRequest,t.timeRequest as timeRequest)" +
            " from Transactions t where t.id = :id")
    CustomTransactionInfo findByIdAndTimeRequest(Long id);
}
