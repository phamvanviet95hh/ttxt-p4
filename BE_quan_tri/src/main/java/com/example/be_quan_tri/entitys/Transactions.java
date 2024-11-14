package com.example.be_quan_tri.entitys;

import com.example.be_quan_tri.dtos.transactions.TransactionDtos;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    private String method;

    private String apiKey;

    private String orgCode;

    private String xRealIp;
    private String idQ;

    private String statusCode;

    private String serviceCodeRequest;

    @Column(unique = true)
    private String requestId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String idRequest;

    private Timestamp timeRequest;

    @ManyToOne
    @JoinColumn(name = "partner_code", nullable = false)
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "service_code", nullable = false)
    private CategoryServices categoryServices;

    public TransactionDtos getVo() {
        TransactionDtos transactionDtos = new TransactionDtos();
        BeanUtils.copyProperties(this, transactionDtos);
        transactionDtos.setServiceCode(this.getCategoryServices().getServiceName());
        transactionDtos.setTimeRequest(this.getTimeRequest().toString());
        transactionDtos.setTransactionId(this.getIdRequest());
        return transactionDtos;

    }
}
