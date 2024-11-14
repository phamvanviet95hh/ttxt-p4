package com.example.be_quan_tri.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class addServiceOfCategoryDto {

    private Long id;
    private Long idPart;
    private ArrayList<Long> data;

}
