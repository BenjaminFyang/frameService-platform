package com.example.accountservice.Entity;


import com.example.accountservice.Enum.TransactionTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VirtualWalletTransactionEntity {


    private BigDecimal amount;


    private Long createTime;


    private TransactionTypeEnum type;


    private Long fromWalletId;
}
