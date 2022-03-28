package com.example.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shardingjdbc.dataobject.OrderDO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<OrderDO> {

}
