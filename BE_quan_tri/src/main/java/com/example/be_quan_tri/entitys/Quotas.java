package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "quotas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Quotas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String quotaCode;
    private Long quotas;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "quotas")
    private Set<QuotasPartnerCategory> quotasPartnerCategory;

    public Quotas(Long idQuota) {
        this.id = idQuota;

    }
}
