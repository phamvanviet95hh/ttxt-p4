package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "category_to_dv")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Category_to_Dv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoryServices category;

    @ManyToOne
    @JoinColumn(name="dv_id", nullable=false)
    private Services services;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
