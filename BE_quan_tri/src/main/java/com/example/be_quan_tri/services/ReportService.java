package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtos;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa1;
import com.example.be_quan_tri.dtos.partners.CustomReportDto;
import com.example.be_quan_tri.dtos.reports.*;
import com.example.be_quan_tri.dtos.transactions.TransactionDtos;
import com.example.be_quan_tri.entitys.Transactions;
import com.example.be_quan_tri.repositorys.GoliveResitory;
import com.example.be_quan_tri.repositorys.ReportRepository;
import com.example.be_quan_tri.repositorys.TransactionRepository;
import com.example.be_quan_tri.repositorys.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {


    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private GoliveResitory goliveResitory;

    @Autowired
    private TransactionRepository transactionRepository;


    public Page<CustomReportDto> findByUpdatedAt(Long partnerId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {

        return userRepository.findByUpdatedAt(partnerId, startDate, endDate, pageable);

    }


    public Page<CustomReportDetailDtos> findByCreatedAt(Long partnerId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return userRepository.findByCreatedAt(partnerId, startDate, endDate, pageable);
    }
    public Page<CustomReportDetailDtosPa> findByCreatedAt2(Long partnerId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {


        return userRepository.findByCreatedAtAndPartnerAddress(partnerId, startDate, endDate, pageable);
    }

    public List<fomatReportServiceDtos> getReportGetAllService(Long partnerId, LocalDateTime timestampYesterday, LocalDateTime timestampToday) {

        return userRepository.findByTimeRequest(partnerId, timestampYesterday, timestampToday);
    }

    public List<CustomReportDetailDtos> getReportGetAllServiceItem(Long partnerId, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return userRepository.findByTimeRequestAndUpdatedAt(partnerId, localDateTimeStart, localDateTimeEnd);
    }

    public List<ReportPartnerDtos> getgetAllPartnerToCate(Long partnerId, Long cateId, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return userRepository.findByPartnerCode(partnerId, cateId, localDateTimeStart, localDateTimeEnd);
    }

    public List<ReportPartnerDtosService> getgetAllPartnerToService(Long partnerId, Long cateId, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return userRepository.findByPartnerCodeAndPartnerName(partnerId, cateId, localDateTimeStart, localDateTimeEnd);
    }

    public Page<ReportCategoryOfPartner> findByCategoryOfPartner(Long partnerId, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd, Pageable pageable) {
        return reportRepository.findByCreatedAt(partnerId, localDateTimeStart, localDateTimeEnd, pageable);
    }

    public ResponseEntity<GloableReportResponse> exportDataListPartnerReport(HttpServletResponse httpServletResponse, Long idPartner, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd, Pageable pageable) throws IOException {
        Page<CustomReportDto> page = userRepository.findByUpdatedAt(idPartner, localDateTimeStart, localDateTimeEnd, pageable);
        excelExportService.exportDataToExcelPartnerReport(httpServletResponse,  page.getContent());
        return new ResponseEntity<>(new GloableReportResponse(), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public void exportDataListCategory(Long partnerId, HttpServletResponse httpServletResponse, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd, Pageable pageable) throws IOException {
        List<fomatReportServiceDtos> totalDataService = userRepository.findByTimeRequest(partnerId, localDateTimeStart, localDateTimeEnd);
        excelExportService.exportDataToExcelCategory(httpServletResponse,  totalDataService);
    }

    public void exportDataListService(Long partnerId, HttpServletResponse httpServletResponse, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) throws IOException {
        List<CustomReportDetailDtos> totalDataService = userRepository.findByTimeRequestAndUpdatedAt(partnerId, localDateTimeStart, localDateTimeEnd);
        excelExportService.exportDataToExcelService(httpServletResponse,  totalDataService);
    }


    public void exportDataListTransaction(Long partnerId, HttpServletResponse httpServletResponse, String partnerCodeTran, String statusTran, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) throws IOException {
        List<Transactions> transactions = transactionRepository.findByTimeRequestAndApiKey(partnerId, partnerCodeTran, statusTran, localDateTimeStart, localDateTimeEnd);
        List<TransactionDtos> transactionDtos = new ArrayList<>();
        for (Transactions item : transactions) {
            transactionDtos.add(item.getVo());
        }
        excelExportService.exportDataToTransaction(httpServletResponse, transactionDtos);

    }

    public Page<CustomReportDetailDtosPa1> findByCreatedAt3(Long idPartner,  Pageable pageable) {
        return userRepository.findByCreatedAtAndPartnerAddressAndUpdatedAt(idPartner, pageable);
    }
}
