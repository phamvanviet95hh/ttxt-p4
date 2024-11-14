package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.entitys.Golive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoliveResitory extends JpaRepository<Golive, Long> {
    @Query("select g from Golive g where g.partner.id = :idPart and g.categoryServices.id = :idCate")
    Golive findByPartner(Long idPart, Long idCate);
}
