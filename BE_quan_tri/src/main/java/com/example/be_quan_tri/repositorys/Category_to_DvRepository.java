package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.entitys.CategoryServices;
import com.example.be_quan_tri.entitys.Category_to_Dv;
import com.example.be_quan_tri.entitys.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface Category_to_DvRepository extends JpaRepository<Category_to_Dv, Long> {

    Category_to_Dv findByCategoryAndServices(CategoryServices categoryServices, Services services);
}
