package com.example.accountservice;

import com.ql.util.express.Operator;

/**
 * 定义一个继承自com.ql.util.express.Operator的操作符
 */
public class ApproveOperator extends Operator {

    private final int operator;

    public ApproveOperator(int op) {
        this.operator = op;
    }

    @Override
    public Object executeInner(Object[] list) {
        if (this.operator == 1) {
            System.out.println(list[0] + "审批:金额:" + list[1]);
            return ((Integer) list[1]) <= 6000;
        } else if (this.operator == 2) {
            System.out.println("报销入卡:金额:" + list[0]);
        } else {
            System.out.println("重填:申请人:" + list[0]);
        }
        return true;
    }
}
