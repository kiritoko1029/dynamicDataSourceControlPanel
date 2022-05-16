package com.chencx.dynamicdatabasespringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chencx.dynamicdatabasespringboot.model.domain.Sqlmap;
import com.chencx.dynamicdatabasespringboot.service.SqlmapService;
import com.chencx.dynamicdatabasespringboot.mapper.SqlmapMapper;
import org.springframework.stereotype.Service;

/**
* @author chenxiangcai
* @description 针对表【SQLMAP】的数据库操作Service实现
* @createDate 2022-05-16 14:16:06
*/
@Service
public class SqlmapServiceImpl extends ServiceImpl<SqlmapMapper, Sqlmap>
    implements SqlmapService{

}




