package com.example.be_quan_tri.controllers.webcontrollers.dashboard;

import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.dashboard.CustomCount;
import com.example.be_quan_tri.dtos.partners.CustomReportDto;
import com.example.be_quan_tri.services.PartnerService;
import com.example.be_quan_tri.services.ReportService;
import com.example.be_quan_tri.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class DashboardWebUserController {
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private TransactionService transactionService;


    @GetMapping("admin/dashboardUser")
    public String dashboardUser(Model model, @RequestParam("userId") String userId,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate
                                ) {

        Long idPartner = Long.valueOf(userId);
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Page<CustomReportDto> page = reportService.findByUpdatedAt(idPartner, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
        Long totalMoney = 0L;
        Long countTransaction = 0L;
        for (CustomReportDto reportDto : page.getContent()) {
            countTransaction = reportDto.getTotalRequests();
            totalMoney = reportDto.getTotalCost();
        }
        model.addAttribute("countTransaction", countTransaction);
        model.addAttribute("totalMoney", totalMoney);
        return "dashboard/thongke/roleUser/thongkeUser";
    }

}
