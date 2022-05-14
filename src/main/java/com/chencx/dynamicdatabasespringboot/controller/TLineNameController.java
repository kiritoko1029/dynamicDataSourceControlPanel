package com.chencx.dynamicdatabasespringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chencx.dynamicdatabasespringboot.model.domain.TLineName;
import com.chencx.dynamicdatabasespringboot.service.TLineNameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lineName")
public class TLineNameController {

    @Resource
    TLineNameService tLineNameService;

    /**
     * @description  获取所有机组及对应的名字
     * @author chenxiangcai
     * @date 2022/5/10 16:00
     * @return com.baosight.tgsgserve.common.BaseResponse<java.util.Map>
     */
    @GetMapping("/getLineName")
    public Map getLineName(){
        QueryWrapper<TLineName> queryWrapper =new QueryWrapper<>();
        queryWrapper.select("LINENO","LINENAME");
        List<TLineName> lineNameList = tLineNameService.list(queryWrapper);
        Map resultMap = new HashMap();
        lineNameList.forEach(name->{
            resultMap.put(name.getLINENO(),name.getLINENAME());
        });
        return resultMap;
    }

}
