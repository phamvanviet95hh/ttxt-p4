package com.example.be_quan_tri.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceName;
    private String serviceDescription;
    private String serviceCode;
    private Long servicePrice;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "services")
    private Set<Category_to_Dv> categories;

    public Services(Long item) {
        this.id = item;
    }
}
