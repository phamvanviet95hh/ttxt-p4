package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "partner_to_services")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Partner_to_Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private CategoryServices service;

    @ManyToOne
    @JoinColumn(name = "service_children_id", nullable = true)
    private Services serviceChildren;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

