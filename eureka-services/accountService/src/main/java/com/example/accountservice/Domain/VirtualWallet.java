package com.example.accountservice.Domain;

import com.central.common.exception.BusinessException;
import lombok.Data;

import java.math.BigDecimal;


// Domain领域模型（充血模型）
@Data
public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    // 是否允许透支
    private boolean isAllowedOverdraft = true;

    // 可透支金额
    private BigDecimal overdraftAmount = BigDecimal.ZERO;

    // 冻结金额
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet() {
    }

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }


    /**
     * 冻结
     *
     * @param amount 冻结金额
     */
    public void freeze(BigDecimal amount) {
    }

    public void unfreeze(BigDecimal amount) {
        // todo
    }

    /**
     * 增加透支金额
     *
     * @param amount 金额
     */
    public void increaseOverdraftAmount(BigDecimal amount) {
        // todo
    }

    /**
     * 减少透支金额
     *
     * @param amount 金额
     */
    public void decreaseOverdraftAmount(BigDecimal amount) {
        // todo
    }


    /**
     * 关闭信用度透支
     */
    public void closeOverdraft() {
        // todo
    }

    /**
     * 打开信用度透支
     */
    public void openOverdraft() {
        // todo
    }

    public BigDecimal balance() {
        return this.balance;
    }

    /**
     * 获得账户的余额
     *
     * @return 余额
     */
    public BigDecimal getAvailableBalance() {
        BigDecimal totalAvailableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvailableBalance = this.overdraftAmount.add(totalAvailableBalance);
        }
        return totalAvailableBalance;
    }

    /**
     * 出账 开始扣减金额.
     *
     * @param amount 剩余账户的余额
     */
    public void debit(BigDecimal amount) {
        BigDecimal totalAvailableBalance = getAvailableBalance();
        if (totalAvailableBalance.compareTo(amount) < 0) {
            throw new BusinessException("账户余额不足");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("入账的金额不能为负数");
        }
        this.balance = this.balance.add(amount);
    }
}
