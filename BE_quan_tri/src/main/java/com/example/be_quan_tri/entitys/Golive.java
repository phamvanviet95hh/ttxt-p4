package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "golive")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Golive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partner_id",nullable=false)
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable=false)
    private CategoryServices categoryServices;

    private LocalDateTime dateGolive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
