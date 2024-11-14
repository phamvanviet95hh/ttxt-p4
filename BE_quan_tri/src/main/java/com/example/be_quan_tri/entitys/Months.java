package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "months")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Months {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long month;
}
