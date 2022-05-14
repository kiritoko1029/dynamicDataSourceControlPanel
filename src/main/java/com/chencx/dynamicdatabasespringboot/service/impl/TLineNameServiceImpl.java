package com.chencx.dynamicdatabasespringboot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chencx.dynamicdatabasespringboot.mapper.TLineNameMapper;
import com.chencx.dynamicdatabasespringboot.model.domain.TLineName;
import com.chencx.dynamicdatabasespringboot.service.TLineNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenxiangcai
* @description 针对表【T_LINE_NAME(机组表)】的数据库操作Service实现
* @createDate 2022-05-10 15:35:52
*/
@Slf4j
@Service
public class TLineNameServiceImpl extends ServiceImpl<TLineNameMapper, TLineName>
    implements TLineNameService {

}




