package com.example.be_quan_tri.entitys;

import com.example.be_quan_tri.dtos.ResponseService;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category_services")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryServices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "service_name", nullable = false, length = 255, unique = true)
    private String serviceName;

    private String serviceDetail;

    private int status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "categoryServices")
    private Set<Transactions> transactionsSet;

    @OneToMany(mappedBy = "service")
    private Set<ReportHistory> reportHistories;

    @OneToMany(mappedBy = "service")
    private Set<Partner_to_Service> partnerToServices;
    @OneToMany(mappedBy = "service")
    private Set<ReportDayEntity> reportDayEntities;

    @OneToMany(mappedBy = "service")
    private Set<QuotasPartnerCategory> quotas;

    @OneToMany(mappedBy = "categoryServices")
    private Set<Golive> golives;

    @OneToMany(mappedBy = "category")
    private Set<Category_to_Dv> categoryToDv;

    public CategoryServices(Long serviceCode) {
        this.id = serviceCode;
    }

    public ResponseService getVo() {

        ResponseService responseService = new ResponseService();
        BeanUtils.copyProperties(this, responseService);
        return responseService;

    }

}
