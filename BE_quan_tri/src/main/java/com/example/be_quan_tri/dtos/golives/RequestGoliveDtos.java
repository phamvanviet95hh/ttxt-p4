package com.example.be_quan_tri.dtos.golives;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestGoliveDtos {

    private Long idPart;
    private Long idCate;
    private LocalDateTime datetime;

}
