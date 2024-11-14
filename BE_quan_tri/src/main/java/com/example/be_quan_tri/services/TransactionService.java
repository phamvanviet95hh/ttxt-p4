package com.example.be_quan_tri.services;

import com.example.be_quan_tri.dtos.dashboard.CustomCount;
import com.example.be_quan_tri.dtos.transactions.CustomTransactionInfo;
import com.example.be_quan_tri.entitys.Transactions;
import com.example.be_quan_tri.repositorys.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExcelExportService excelExportService;

    public Page<Transactions> getTransactionsPaginated(Long partnerId, String partnerCode, String status, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return transactionRepository.findByTimeRequest(partnerId, partnerCode, status, start, end, pageable);
    }

    public void getTransactionsExport(String partnerCodeTran, String statusTran, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {

    }

    public CustomTransactionInfo getTransactionsInfo(Long id) {
        return transactionRepository.findByIdAndTimeRequest(id);
    }

    public Long countToltalTransactionSuccess(Long idPartner, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd ) {
        return transactionRepository.findByIdAndCreatedAt(idPartner, localDateTimeStart, localDateTimeEnd);
    }

    public Long totalMoney(Long idPartner, LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return transactionRepository.findByIdAndUpatedAt(idPartner, localDateTimeStart, localDateTimeEnd);
    }
}
