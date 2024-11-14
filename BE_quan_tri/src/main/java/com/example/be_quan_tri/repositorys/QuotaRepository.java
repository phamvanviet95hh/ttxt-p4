package com.example.be_quan_tri.repositorys;

import com.example.be_quan_tri.entitys.Quotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quotas, Long> {
}
