package com.example.orderservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.SysRole;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;


/**
 * @author zlt
 * 角色
 */
@Mapper
public interface SysRoleMapper extends SuperMapper<SysRole> {
    List<SysRole> findList(Page<SysRole> page, @Param("r") Map<String, Object> params);

    List<SysRole> findAll();
}
