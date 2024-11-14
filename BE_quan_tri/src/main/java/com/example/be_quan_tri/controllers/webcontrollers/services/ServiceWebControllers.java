package com.example.be_quan_tri.controllers.webcontrollers.services;

import com.example.be_quan_tri.comparator.ServiceComparator;
import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.partners.CustomCategoryDtos;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPa1;
import com.example.be_quan_tri.dtos.partners.CustomReportDetailDtosPaNew;
import com.example.be_quan_tri.dtos.services.CategoryServicesDtos;
import com.example.be_quan_tri.entitys.Services;
import com.example.be_quan_tri.services.DvServices;
import com.example.be_quan_tri.services.ReportService;

import org.springframework.ui.Model;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.services.CategoryServiceSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("services/")
public class ServiceWebControllers {

    @Autowired
    private DvServices dvServices;

    @Autowired
    private ReportService reportService;

    @Autowired
    private CategoryServiceSV categoryService;


    @GetMapping("listCategoryService")
    public String listService(
                              @RequestParam String size,
                              @RequestParam String page, Model model) {
        int sizeInt = Integer.parseInt(size);
        int pageInt = Integer.parseInt(page);
        Pageable pageable = PageRequest.of(pageInt, sizeInt);
        Page<CategoryServices> serviceSVS = categoryService.getAllCategoryService(pageable);
        model.addAttribute("serviceSVS", serviceSVS);
        return "dashboard/services/listCategoryService";
    }

    @GetMapping("listService")
    public String listCategoryService(
            @RequestParam String size,
            @RequestParam String page, Model model) {
        int sizeInt = Integer.parseInt(size);
        int pageInt = Integer.parseInt(page);
        Pageable pageable = PageRequest.of(pageInt, sizeInt);
        Page<Services> services = dvServices.getAllService(pageable);
        model.addAttribute("services", services);
        return "dashboard/services/listService";
    }


    @GetMapping("editService")
    public String tableService(
            @RequestParam String id, Model model) {
        Long idService = Long.valueOf(id);
        Services services = dvServices.getOneService(idService);
        model.addAttribute("idService", idService);
        model.addAttribute("services", services);
        return "dashboard/services/inforService";
    }

    @GetMapping("loadService")
    public String loadService(
            @RequestParam String size,
            @RequestParam String idPart,
                               @RequestParam String page,
            @RequestParam String id, Model model) {
        int sizeInt = Integer.parseInt(size);
        int pageInt = Integer.parseInt(page);
        Long idCategory = Long.valueOf(id);
        Long idPartner = Long.valueOf(idPart);
        Pageable pageable = PageRequest.of(pageInt, sizeInt);
        Page<Services> services = dvServices.getAllService(pageable);
        Page<CustomListDataServiceOfCategoryDto> categoryDtos = dvServices.getAllServiceOfCategory(idPartner, idCategory, pageable);
        model.addAttribute("idCategory", idCategory);
        model.addAttribute("idPartner", idPartner);
        model.addAttribute("categoryDtos", categoryDtos);
        model.addAttribute("services", services);
        model.addAttribute("productComparator", new ServiceComparator());
        return "dashboard/services/loadListService";
    }

    @GetMapping("loadCategoryService")
    public String loadCategoryService(
            @RequestParam String id, Model model) {
        Long idPartner = Long.valueOf(id);
        List<CategoryServicesDtos> categoryServices = categoryService.getAllCategoryServiceinPart();
        List<CustomCategoryDtos> categoryDtos = categoryService.getAllServiceOfCategory(idPartner);
        model.addAttribute("categoryServices", categoryServices);
        model.addAttribute("idPartner", idPartner);
        model.addAttribute("categoryDtos", categoryDtos);
        model.addAttribute("productComparator", new ServiceComparator());
        return "dashboard/services/loadCategoryService";
    }

    @GetMapping("loadServiceForPartner")
    public String loadServiceForPartner(
        @RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate,
            @RequestParam String id, Model model) {
        Long idPartner = Long.valueOf(id);
        LocalDateTime localDateTimeStart = LocalDateTime.parse(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(endDate);
        Page<CustomReportDetailDtosPa1> page3 = reportService.findByCreatedAt3(idPartner, GloableValue.pageAndId("10", "0"));
        Page<CustomReportDetailDtosPa> page2 = reportService.findByCreatedAt2(idPartner, localDateTimeStart, localDateTimeEnd, GloableValue.pageAndId("10", "0"));
        List<CustomReportDetailDtosPaNew> serviceDtosNews = new ArrayList<>();
        for (CustomReportDetailDtosPa dtos : page2) {
            serviceDtosNews.add(dtos.getVo());
        }


        // Tính toán rowspan cho từng ReportService dựa vào SateServiceName
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (CustomReportDetailDtosPaNew report : serviceDtosNews) {
            String name = report.getCategoryName();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }

        // Gán giá trị rowspan cho từng ReportService
        for (CustomReportDetailDtosPaNew report : serviceDtosNews) {
            report.setRowspan(nameCountMap.get(report.getCategoryName()) > 1 ? 2 : 1);
        }
        model.addAttribute("idPartner", idPartner);
        model.addAttribute("serviceDtosNews", serviceDtosNews);
        model.addAttribute("page3", page3);
        model.addAttribute("productComparator", new ServiceComparator());
        return "dashboard/partner/service";
    }

}
