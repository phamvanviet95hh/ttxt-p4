package com.example.be_quan_tri.entitys;

import com.example.be_quan_tri.dtos.ResponsePartner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "partners")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "partner_name", nullable = false, length = 255)
    private String partnerName;

    private String userName;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime goLiveDate;
    private LocalDateTime dateEndLive;

    private String role;

    private String status;

    @Column(unique = true, nullable = false)
    private String partnerCode;

    private String partnerDetail;
    private String partnerAddress;
    private String partnerTax;
    private String partnerEmail;
    private String partnerUser;
    private String partnerPhone;

    @Column(columnDefinition = "TEXT")
    private String partnerLogo;

    @OneToMany(mappedBy = "partner")
    private Set<Transactions> transactionsSet;

    @OneToMany(mappedBy = "partner")
    private Set<ReportDayEntity> reportDayEntities;

    @OneToMany(mappedBy = "partner")
    private Set<Partner_to_Service> partnerToServices;

    @OneToMany(mappedBy = "partner")
    private Set<ReportHistory> reportHistories;
    @OneToMany(mappedBy = "partner")
    private Set<QuotasPartnerCategory> quotas;

    @OneToMany(mappedBy = "partner")
    private Set<Golive> golives;

    public Partner(Long i) {
        this.id = i;
    }

    public ResponsePartner getVo() {
        ResponsePartner responsePartner = new ResponsePartner();
        BeanUtils.copyProperties(this, responsePartner);
        return responsePartner;
    }
}
