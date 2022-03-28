package com.example.accountservice;

import com.ql.util.express.Operator;

import java.util.ArrayList;
import java.util.List;

public class JoinOperator extends Operator {

    public Object executeInner(Object[] list) {
        List result = new ArrayList();
        Object opdata1 = list[0];
        if (opdata1 instanceof List) {
            result.addAll((List) opdata1);
        } else {
            result.add(opdata1);
        }
        for (int i = 1; i < list.length; i++) {
            result.add(list[i]);
        }
        return result;
    }
}
