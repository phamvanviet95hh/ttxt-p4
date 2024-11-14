package com.example.be_quan_tri.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportHisory")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private CategoryServices service;

    private Long totalRequestSuccess;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
