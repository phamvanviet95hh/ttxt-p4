package com.example.be_quan_tri.controllers.webcontrollers;

import com.example.be_quan_tri.dtos.GloableValue;
import com.example.be_quan_tri.dtos.dashboard.CustomThongKeAdmin;
import com.example.be_quan_tri.dtos.dashboard.CustomTopPartner;
import com.example.be_quan_tri.services.CategoryServiceSV;
import com.example.be_quan_tri.services.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller

public class WebControllers {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private CategoryServiceSV categoryServiceSV;

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("dashboard")
    public String dashboard(
            @RequestParam String idPart,
            Model model
    ){
        Long idPartner = !Objects.equals(idPart, "") ? Long.valueOf(idPart) : null;

        return "dashboard/dashboard";
    }

    @GetMapping("admin/dashboard")
    public String adminDashboard(Model model){
        Long countPartner = partnerService.countPartner();
        Long countCate = categoryServiceSV.countCate();
        List<CustomTopPartner> customTopPartners = partnerService.getTopPartner(GloableValue.pageAndId("5", "0"));
        model.addAttribute("partner", countPartner);
        model.addAttribute("category", countCate);
        model.addAttribute("customTopPartners", customTopPartners);
        return "dashboard/thongke/thongkeAdmin";
    }


}
