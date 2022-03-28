package com.example.accountservice.service;

import java.math.BigDecimal;

public interface VirtualWalletService {

    // 查询余额
    BigDecimal getBalance(Long walletId);

    // 出账
    void debit(Long walletId, BigDecimal amount);

    // 入账
    void credit(Long walletId, BigDecimal amount);

    // 转账
    void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount);
}
