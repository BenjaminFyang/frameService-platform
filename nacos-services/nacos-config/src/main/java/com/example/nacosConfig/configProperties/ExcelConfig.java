package com.example.nacosConfig.configProperties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "excel")
public class ExcelConfig {

    /**
     * 账单导出模板路径
     */
    private String billUrl;

    /**
     * 账户导出模板路径
     */
    private String accountUrl;

    /**
     * 余额/专项导入 再生成导入文件模板
     */
    private String balanceImportRecordUrl;

    /**
     * 退费处理记录详情模板
     */
    private String refundDeductionDetailUrl;

    /**
     * 专项余额明细账详情模板
     */
    private String accountSpecialAmountChangeRecordDetailUrl;

    /**
     * 专项票额明细账详情模板
     */
    private String accountSpecialInvoiceChangeRecordDetailUrl;


    public String getBillUrl() {
        return billUrl;
    }

    public void setBillUrl(String billUrl) {
        this.billUrl = billUrl;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public String getBalanceImportRecordUrl() {
        return balanceImportRecordUrl;
    }

    public void setBalanceImportRecordUrl(String balanceImportRecordUrl) {
        this.balanceImportRecordUrl = balanceImportRecordUrl;
    }

    public String getRefundDeductionDetailUrl() {
        return refundDeductionDetailUrl;
    }

    public void setRefundDeductionDetailUrl(String refundDeductionDetailUrl) {
        this.refundDeductionDetailUrl = refundDeductionDetailUrl;
    }

    public String getAccountSpecialAmountChangeRecordDetailUrl() {
        return accountSpecialAmountChangeRecordDetailUrl;
    }

    public void setAccountSpecialAmountChangeRecordDetailUrl(String accountSpecialAmountChangeRecordDetailUrl) {
        this.accountSpecialAmountChangeRecordDetailUrl = accountSpecialAmountChangeRecordDetailUrl;
    }

    public String getAccountSpecialInvoiceChangeRecordDetailUrl() {
        return accountSpecialInvoiceChangeRecordDetailUrl;
    }

    public void setAccountSpecialInvoiceChangeRecordDetailUrl(String accountSpecialInvoiceChangeRecordDetailUrl) {
        this.accountSpecialInvoiceChangeRecordDetailUrl = accountSpecialInvoiceChangeRecordDetailUrl;
    }
}