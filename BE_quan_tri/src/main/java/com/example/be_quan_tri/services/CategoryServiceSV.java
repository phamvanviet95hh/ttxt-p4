package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.CustomListDataServiceOfCategoryDto;
import com.example.be_quan_tri.dtos.partners.CustomCategoryDtos;
import com.example.be_quan_tri.dtos.services.CategoryServicesDtos;
import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.repositorys.CategoryServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceSV {

    @Autowired
    private CategoryServicesRepository categoryServicesRepository;

    public Page<CategoryServices> getAllCategoryService(Pageable pageable) {
        return categoryServicesRepository.findAll(pageable);
    }

    public CategoryServices getInFoCategory(Long idCategory) {
        return categoryServicesRepository.findById(idCategory).get();
    }

    public Long countCate() {
        return categoryServicesRepository.count();
    }

    public List<CategoryServicesDtos> getAllCategoryServiceinPart() {
        return categoryServicesRepository.findByCreatedAt();
    }

    public List<CustomCategoryDtos> getAllServiceOfCategory(Long idPartner) {

        return categoryServicesRepository.findByIdAndCreatedAt(idPartner);

    }
}
