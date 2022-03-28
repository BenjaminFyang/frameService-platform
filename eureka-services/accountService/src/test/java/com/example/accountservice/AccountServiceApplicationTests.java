package com.example.accountservice;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
public class AccountServiceApplicationTests {

    public void println(String s) {
        System.out.println(s);
    }

    /**
     * 绑定java类或者对象的method
     *
     * @param abc 需要转换大小的字符串.
     * @return the String
     */
    public static String upper(String abc) {
        return abc.toUpperCase();
    }


    /**
     * 替换 if then else 等关键字
     *
     * @throws Exception 异常抛出
     */
    @Test
    public void test1() throws Exception {
        ExpressRunner runner = new ExpressRunner();

        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);

        String express = "如果 (语文 + 数学 + 英语 > 270) 则 {return 1;} 否则 {return 0;}";
        DefaultContext<String, Object> context = new DefaultContext<>();
        Object execute = runner.execute(express, context, null, false, false, null);
        log.info("返回值:" + execute.toString());
    }


    // 如何自定义和使用Operator
    @Test
    public void test2() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        runner.addFunction("join", new ApproveOperator(1));

        String express = "join('客户经理', 2000)";
        Object execute = runner.execute(express, context, null, false, false, null);
        log.info("返回值:" + execute.toString());
    }

    /**
     * 绑定java类或者对象的method.
     *
     * @throws Exception 数据异常.
     */
    @Test
    public void test4() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addFunctionOfClassMethod("转换为大写", AccountServiceApplicationTests.class.getName(), "com.example.accountservice.upper", new String[]{"String"}, null);
        String express = "转换为大写(\"hello world\")";
        DefaultContext<String, Object> context = new DefaultContext<>();
        Object execute = runner.execute(express, context, null, false, false, null);
        log.info("返回值:" + execute.toString());
    }

    /**
     * macro 宏定义<可以在导入的时候做参数验证></>
     *
     * @throws Exception 异常数据
     */
    @Test
    public void test5() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");
        IExpressContext<String, Object> context = new DefaultContext<>();
        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 95);
        Object result = runner.execute("是否优秀", context, null, false, false);
        log.info("是否优秀:" + result);

        Object execute = runner.execute("计算平均成绩", context, null, false, false);
        log.info("计算平均成绩:" + execute);
    }


    /**
     * 获取一个表达式需要的外部变量名称列表
     *
     * @throws Exception 异常信息.
     */
    @Test
    public void test6() throws Exception {
        String express = "int 平均分 = (语文 + 数学 + 英语 + 综合考试.科目2) / 4.0; return 平均分";
        ExpressRunner runner = new ExpressRunner(true, true);
        String[] names = runner.getOutVarNames(express);
        for (String s : names) {
            log.info("var:" + s);
        }
    }

    /**
     * 集合的快捷写法
     *
     * @throws Exception 异常信息
     */
    @Test
    public void test7() throws Exception {

        ExpressRunner runner = new ExpressRunner(false, false);
        DefaultContext<String, Object> context = new DefaultContext<>();

        String express = "abc = NewMap(1:1, 2:2); return abc.get(1) + abc.get(2);";
        Object r = runner.execute(express, context, null, false, false);
        log.info("r :" + r);

        express = "abc = NewList(1, 2, 3); return abc.get(1) + abc.get(2)";
        r = runner.execute(express, context, null, false, false);
        log.info("r :" + r);

        express = "abc = [1, 2, 3]; return abc[1] + abc[2];";
        r = runner.execute(express, context, null, false, false);
        log.info("r :" + r);
    }

    /**
     * 执行一段文本
     *
     * @throws Exception 异常抛出
     */
    @Test
    public void testApprove1() throws Exception {
        String express = ""
                + "if (审批通过(经理, 金额)) {\n"
                + "    if (金额 > 5000) {\n"
                + "        if (审批通过(总监, 金额)) {\n"
                + "            if (审批通过(财务, 金额)) {\n"
                + "                报销入账(金额)\n"
                + "            } else {\n"
                + "                打回修改(申请人)\n"
                + "            }\n"
                + "        } else {\n"
                + "            打回修改(申请人)\n"
                + "        }\n"
                + "    } else {\n"
                + "        if (审批通过(财务, 金额)) {\n"
                + "            报销入账(金额)\n"
                + "        } else {\n"
                + "            打回修改(申请人)\n"
                + "        }\n"
                + "    }\n"
                + "} else {\n"
                + "    打回修改(申请人)\n"
                + "}\n"
                + "打印(\"完成\")\n";
        System.out.println("express = " + express);
        ExpressRunner runner = new ExpressRunner();

        // 定义操作符别名
        runner.addFunctionOfServiceMethod("打印", new AccountServiceApplicationTests(), "println", new String[]{"String"}, null);

        // 定义方法
        runner.addFunction("审批通过", new ApproveOperator(1));
        runner.addFunction("报销入账", new ApproveOperator(2));
        runner.addFunction("打回修改", new ApproveOperator(3));

        // 设置上下文变量
        IExpressContext<String, Object> expressContext = new DefaultContext<>();
        expressContext.put("经理", "王经理");
        expressContext.put("总监", "李总监");
        expressContext.put("财务", "张财务");
        expressContext.put("申请人", "小强");
        expressContext.put("金额", 4000);

        runner.execute(express, expressContext, null, false, false);
    }

}
