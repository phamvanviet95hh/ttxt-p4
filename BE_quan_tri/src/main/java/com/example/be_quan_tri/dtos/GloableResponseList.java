package com.example.be_quan_tri.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GloableResponseList<T> {
    private Boolean success;
    private String message;
    private List<T> data;
}
