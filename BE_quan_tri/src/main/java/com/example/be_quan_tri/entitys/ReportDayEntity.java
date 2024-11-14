package com.example.be_quan_tri.entitys;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDayEntity {

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
    private Long totalRequestFailure;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
