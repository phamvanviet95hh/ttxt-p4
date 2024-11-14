package com.example.be_quan_tri.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@Builder
public class GloableValue {
    public static Pageable pageAndId(String size, String page){
        int sizeInt = Integer.parseInt(size);
        int pageInt = Integer.parseInt(page);
        Pageable pageable = PageRequest.of(pageInt, sizeInt);
        return pageable;
    }

}
