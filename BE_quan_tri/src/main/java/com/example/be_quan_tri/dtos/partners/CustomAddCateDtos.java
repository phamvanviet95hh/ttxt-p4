package com.example.be_quan_tri.dtos.partners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomAddCateDtos {

    private Long idPartner;
    private ArrayList<Long> data;

}
