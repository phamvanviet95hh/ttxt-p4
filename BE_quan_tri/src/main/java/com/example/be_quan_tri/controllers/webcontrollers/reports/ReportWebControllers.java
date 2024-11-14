package com.example.be_quan_tri.controllers.webcontrollers.reports;

import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtos;
import com.example.be_quan_tri.dtos.partners.CustomReportDto;
import com.example.be_quan_tri.dtos.reports.*;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.services.PartnerService;
import com.example.be_quan_tri.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("reports/")
public class ReportWebControllers {

    @Autowired
    private ReportService reportService;

    @Autowired
    private PartnerService partnerService;

    @GetMapping("listReport")
    public String listPartner(@RequestParam("id") String id,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              Model model) {
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        Long idPartner = !Objects.equals(id, "") ? Long.valueOf(id) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Page<CustomReportDto> page = reportService.findByUpdatedAt(idPartner, localDateTimeStart, localDateTimeEnd,GloableValue.pageAndId("10", "0"));
        List<Partner> partners = partnerService.findAll();
        Long totalMoney = 0L;
        for (CustomReportDto dtos : page) {
            totalTransaction += dtos.getTotalRequests();
            totalTransactionSuccess += dtos.getTotalSuccessfulRequests();
            totalMoney += dtos.getTotalCost();
        }
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("listPartner", partners);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        model.addAttribute("page", page);
        return "dashboard/reports/listReport";
    }

    @GetMapping("findListReport")
    public String findListReport(@RequestParam("id") String id,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              Model model) {
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        Long idPartner = !Objects.equals(id, "") ? Long.valueOf(id) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Page<CustomReportDto> page = reportService.findByUpdatedAt(idPartner, localDateTimeStart, localDateTimeEnd,GloableValue.pageAndId("10", "0"));
        List<Partner> partners = partnerService.findAll();
        Long totalMoney = 0L;
        for (CustomReportDto dtos : page) {
            totalTransaction += dtos.getTotalRequests();
            totalTransactionSuccess += dtos.getTotalSuccessfulRequests();
            totalMoney += dtos.getTotalCost();
        }
        model.addAttribute("listPartner", partners);
        model.addAttribute("page", page);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        model.addAttribute("totalMoney", totalMoney);
        return "dashboard/reports/findListReport";
    }

    @GetMapping("listDetail")
    public String listDetail(@RequestParam("id") String idPartner,
                             @RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate,
                             Model model) {

        Long partnerId = Long.valueOf(idPartner);
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Partner partner = partnerService.findById(partnerId);
        Page<CustomReportDetailDtos> page2 = reportService.findByCreatedAt(partnerId, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
        Page<ReportCategoryOfPartner> reportCategoryOfPartners = reportService.findByCategoryOfPartner(partnerId, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
        Long totalMoney = 0L;
        Long totalTransactionCate = 0L;
        Long totalTransactionSucceessCate = 0L;
        for (CustomReportDetailDtos dtos : page2) {
            totalTransaction += dtos.getTotalRequest();
            totalTransactionSuccess += dtos.getTotalSuccessfulRequests();
            totalMoney += dtos.getTotalCost();
        }
        for (ReportCategoryOfPartner dtos : reportCategoryOfPartners) {
            totalTransactionCate += dtos.getTotalTransaction();
            totalTransactionSucceessCate += dtos.getTotalTransactionSuccess();
        }
        model.addAttribute("page", page2);
        model.addAttribute("reportCategoryOfPartners", reportCategoryOfPartners);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransactionCate", totalTransactionCate);
        model.addAttribute("totalTransactionSucceessCate", totalTransactionSucceessCate);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("namePartner", partner.getPartnerCode());
        model.addAttribute("namePartner", partner.getPartnerCode());
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        return "dashboard/reports/listDetailReport";
    }

    @GetMapping("getAllService")
    public String reportAllService(Model model,@RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate, @RequestParam("id") String idPartner) {
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        List<fomatReportServiceDtos> totalDataService = reportService.getReportGetAllService(partnerId, localDateTimeStart, localDateTimeEnd);

        List<fomatReportServiceDtosNew> serviceDtosNews = new ArrayList<>();
        Long totalMoney = 0L;
        for (fomatReportServiceDtos dtos : totalDataService) {
            serviceDtosNews.add(dtos.getVo());
            totalTransaction += dtos.getTotalTransaction();
            totalTransactionSuccess += dtos.getTotalTransactionSuccess();
            totalMoney += dtos.getTotalMoney();
        }


        // Tính toán rowspan cho từng ReportService dựa vào SateServiceName
        Map<String, Integer> nameCountMap = new HashMap<>();
        int count = 1;
        for (fomatReportServiceDtosNew report : serviceDtosNews) {

            String name = report.getSateServiceName();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }
        String lastName = null;
        // Gán giá trị rowspan cho từng ReportService
        for (fomatReportServiceDtosNew report : serviceDtosNews) {
            String name = report.getSateServiceName();
            if (!name.equals(lastName)) {
                // Chỉ gán rowspan cho hàng đầu tiên của mỗi SateServiceName
                report.setRowspan(nameCountMap.get(name));
                lastName = name;
            } else {
                // Các hàng trùng tiếp theo không cần rowspan
                report.setRowspan(0);
            }
        }
        model.addAttribute("serviceDtosNews", serviceDtosNews);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        model.addAttribute("partnerId", partnerId);
        return "dashboard/reports/reportServiceTotal";
    }

    @GetMapping("loadReportCategory")
    public String loadReportCategory(Model model,@RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate, @RequestParam("id") String idPartner) {
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        List<fomatReportServiceDtos> totalDataService = reportService.getReportGetAllService(partnerId, localDateTimeStart, localDateTimeEnd);

        List<fomatReportServiceDtosNew> serviceDtosNews = new ArrayList<>();
        Long totalMoney = 0L;
        for (fomatReportServiceDtos dtos : totalDataService) {
            serviceDtosNews.add(dtos.getVo());
            totalMoney += dtos.getTotalMoney();
            totalTransaction += dtos.getTotalTransaction();
            totalTransactionSuccess += dtos.getTotalTransactionSuccess();
        }


        // Tính toán rowspan cho từng ReportService dựa vào SateServiceName
        Map<String, Integer> nameCountMap = new HashMap<>();
        int count = 1;
        for (fomatReportServiceDtosNew report : serviceDtosNews) {

            String name = report.getSateServiceName();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }
        String lastName = null;
        // Gán giá trị rowspan cho từng ReportService
        for (fomatReportServiceDtosNew report : serviceDtosNews) {
            String name = report.getSateServiceName();
            if (!name.equals(lastName)) {
                // Chỉ gán rowspan cho hàng đầu tiên của mỗi SateServiceName
                report.setRowspan(nameCountMap.get(name));
                lastName = name;
            } else {
                // Các hàng trùng tiếp theo không cần rowspan
                report.setRowspan(0);
            }
        }
        model.addAttribute("serviceDtosNews", serviceDtosNews);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        return "dashboard/reports/loadReportCategory";
    }
    @GetMapping("getAllServiceItem")
    public String getAllServiceItem(Model model,@RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate, @RequestParam("id") String idPartner) {
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        List<CustomReportDetailDtos> totalDataService = reportService.getReportGetAllServiceItem(partnerId,  localDateTimeStart, localDateTimeEnd);
        Long totalMoney = 0L;
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        for (CustomReportDetailDtos dtos : totalDataService) {
            totalTransaction += dtos.getTotalRequest();
            totalTransactionSuccess += dtos.getTotalSuccessfulRequests();
            totalMoney += dtos.getTotalCost();
        }
        model.addAttribute("serviceDtosNews", totalDataService);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        model.addAttribute("partnerId", partnerId);
        return "dashboard/reports/reportServiceDetail";
    }

    @GetMapping("loadAllService")
    public String loadAllService(Model model,@RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate , @RequestParam("id") String idPartner) {
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        List<CustomReportDetailDtos> totalDataService = reportService.getReportGetAllServiceItem(partnerId, localDateTimeStart, localDateTimeEnd);
        Long totalMoney = 0L;
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        for (CustomReportDetailDtos dtos : totalDataService) {
            totalMoney += dtos.getTotalCost();
            totalTransaction += dtos.getTotalRequest();
            totalTransactionSuccess += dtos.getTotalSuccessfulRequests();
        }
        model.addAttribute("serviceDtosNews", totalDataService);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        return "dashboard/reports/loadAllService";
    }

    @GetMapping("getAllPartnerToCate")
    public String getAllPartnerToCate(Model model,@RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate, @RequestParam("id") String idCate,
                                      @RequestParam("idPartner") String idPartner) {
        Long CateId = Long.valueOf(idCate);
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        List<ReportPartnerDtos> totalDataService = reportService.getgetAllPartnerToCate(partnerId, CateId, localDateTimeStart, localDateTimeEnd);
        for (ReportPartnerDtos dtos : totalDataService) {
            totalTransaction += dtos.getTotalTransaction();
            totalTransactionSuccess += dtos.getTotalTransactionSuccess();
        }
        model.addAttribute("serviceDtosNews", totalDataService);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        return "dashboard/reports/listDetailReportPartner";
    }

    @GetMapping("getAllPartnerToService")
    public String getAllPartnerToService(Model model,@RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate, @RequestParam("id") String idService,
                                         @RequestParam("idPartner") String idPartner
    ) {
        Long CateId = Long.valueOf(idService);
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Long partnerId = !Objects.equals(idPartner, "") ? Long.valueOf(idPartner) : null;
        Long totalTransaction = 0L;
        Long totalTransactionSuccess = 0L;
        List<ReportPartnerDtosService> totalDataService = reportService.getgetAllPartnerToService(partnerId, CateId, localDateTimeStart, localDateTimeEnd);
        Long totalMoney = 0L;
        for (ReportPartnerDtosService dtos : totalDataService) {
            totalMoney += dtos.getTotalMoney();
            totalTransaction += dtos.getTotalTransaction();
            totalTransactionSuccess += dtos.getTotalTransactionSuccess();
        }
        model.addAttribute("serviceDtosNews", totalDataService);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("totalTransaction", totalTransaction);
        model.addAttribute("totalTransactionSuccess", totalTransactionSuccess);
        return "dashboard/reports/listDetailReportPartnerService";
    }
}
