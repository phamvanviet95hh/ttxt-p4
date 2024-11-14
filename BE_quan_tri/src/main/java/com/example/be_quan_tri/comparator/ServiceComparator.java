package com.example.be_quan_tri.comparator;

import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.partners.CustomCategoryDtos;
import com.example.be_quan_tri.dtos.services.CategoryServicesDtos;
import com.example.be_quan_tri.entitys.Services;

import java.util.List;
import java.util.Objects;

public class ServiceComparator {

    // Kiểm tra xem ProductA có trong danh sách ProductB dựa trên id không
    public boolean isInList(Services productA, List<CustomListDataServiceOfCategoryDto> listB) {
        for (CustomListDataServiceOfCategoryDto b : listB) {
            if (Objects.equals(b.getServiceId(), productA.getId())) {
                return true;
            }
        }
        return false;
    }
    public boolean isInList2(CategoryServicesDtos productA, List<CustomCategoryDtos> listB) {
        for (CustomCategoryDtos b : listB) {
            if (Objects.equals(b.getCategoryServiceId(), productA.getId())) {
                return true;
            }
        }
        return false;
    }

}
