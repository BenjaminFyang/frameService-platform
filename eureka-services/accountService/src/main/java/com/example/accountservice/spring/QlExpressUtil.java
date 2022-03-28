package com.example.accountservice.spring;

import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * （1）打通了spring容器，通过扩展IExpressContext->QLExpressContext
 * 获取本地变量的时候，可以获取到spring的bean
 * （2）在runner初始化的时候，使用了函数映射功能：addFunctionOfServiceMethod
 * （3）在runner初始化的时候，使用了代码映射功能：addMacro
 */

@Component
public class QlExpressUtil implements ApplicationContextAware {


    private static final ExpressRunner runner;

    static {
        runner = new ExpressRunner();
    }

    private static boolean isInitialRunner = false;
    private ApplicationContext applicationContext;// spring上下文

    /**
     * @param statement 执行语句
     * @param context   上下文
     * @throws Exception 异常处理.
     */
    public Object execute(String statement, Map<String, Object> context) throws Exception {
        initRunner();
        IExpressContext<String, Object> expressContext = new QLExpressContext(context, applicationContext);
        return runner.execute(statement, expressContext, null, true, false);
    }

    private void initRunner() {

        if (isInitialRunner) {
            return;
        }

        synchronized (QlExpressUtil.runner) {
            if (isInitialRunner) {
                return;
            }
            try {

                QlExpressUtil.runner.addFunctionOfServiceMethod("读取用户信息", applicationContext.getBean("bizLogicBean"), "", new Class[]{String.class}, null);
                QlExpressUtil.runner.addMacro("判定用户是否vip", "userDO.salary>200000");

            } catch (Exception e) {
                throw new RuntimeException("初始化失败表达式", e);
            }
        }
        isInitialRunner = true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
