package com.example.be_quan_tri.dtos.transactions;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionDtos {

    private Long id;

    private String path;

    private String method;

    private String apiKey;

    private String orgCode;

    private String xRealIp;
    private String idQ;

    private String statusCode;

    private String serviceCodeRequest;
    private String requestId;
    private String serviceCode;

    private String timeRequest;
    private String transactionId;

}
