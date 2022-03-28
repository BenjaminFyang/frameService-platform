package com.example.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shardingjdbc.dataobject.OrderDO;
import com.example.shardingjdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// https://blog.csdn.net/qq_31142553/article/details/117381360⁄
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelectById() {
        OrderDO order = orderMapper.selectById(1);
        System.out.println(order);
    }

    @Test
    public void testSelectListByUserId() {
        List<OrderDO> orders = orderMapper.selectList(new LambdaQueryWrapper<OrderDO>().eq(OrderDO::getUserId, 330));
        System.out.println("查询出来的订单数据为:order=" + JSONValue.toJSONString(orders));
    }

    @Test
    public void testInsert() {
        OrderDO order = new OrderDO();
        order.setUserId(5678);
        orderMapper.insert(order);
    }

    @Test
    public void selectAll() {
        List<OrderDO> orderDOList = orderMapper.selectList(new LambdaQueryWrapper<>());
        System.out.println("查询出来的订单集合为orderDOList" + JSONValue.toJSONString(orderDOList));
    }

    @Test
    public void testSelectById02() {
        // 测试强制访问主库
        try (HintManager hintManager = HintManager.getInstance()) {
            // 设置强制访问主库
            hintManager.setMasterRouteOnly();
            // 执行查询
            OrderDO order = orderMapper.selectById(1);
            System.out.println(order);
        }
    }


}
