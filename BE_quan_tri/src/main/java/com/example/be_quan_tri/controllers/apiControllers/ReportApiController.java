package com.example.be_quan_tri.controllers.apiControllers;

import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.reports.GloableReportResponse;
import com.example.be_quan_tri.entitys.Transactions;
import com.example.be_quan_tri.services.ReportService;
import com.example.be_quan_tri.services.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("reports/")
public class ReportApiController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ReportService reportService;

    @GetMapping("exportDataListPartnerReport")
    public ResponseEntity<GloableReportResponse> exportDataListPartnerReport(
            HttpServletResponse httpServletResponse,
            @RequestParam("id") String partnerId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws IOException {
        Long idPartner = !Objects.equals(partnerId, "") ? Long.valueOf(partnerId) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        return reportService.exportDataListPartnerReport(httpServletResponse, idPartner, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
    }
    @GetMapping("exportDataListCategory")
    public void exportDataListCategory(
            HttpServletResponse httpServletResponse,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate, @RequestParam("id") String idPartner) throws IOException {
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        reportService.exportDataListCategory(partnerId, httpServletResponse, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
    }

    @GetMapping("exportDataListAllService")
    public void exportDataListAllService(
            HttpServletResponse httpServletResponse,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("id") String idPartner) throws IOException {
        Long partnerIdartner = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        reportService.exportDataListService(partnerIdartner, httpServletResponse, localDateTimeStart, localDateTimeEnd);
    }

    @GetMapping("exportDataTransaction")
    public void exportDataTransaction(
            HttpServletResponse httpServletResponse,
            @RequestParam("status") String status,
            @RequestParam("partnerCode") String partnerCode,
            @RequestParam("startDate") String startDate,
            @RequestParam("id") String idPartner,
            @RequestParam("endDate") String endDate) throws IOException {
        String statusTran = !Objects.equals(status, "") ? status : null;
        String partnerCodeTran = !Objects.equals(partnerCode, "") ? partnerCode : null;
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        reportService.exportDataListTransaction(partnerId, httpServletResponse, partnerCodeTran, statusTran,localDateTimeStart, localDateTimeEnd);
    }

}
