package com.chencx.dynamicdatabasespringboot.common;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义拦截器实现
 * @author chenxiangcai
 * @date 2022/6/16 09:57
 */

@Slf4j
@Configuration
public class DynamicDatasourceClearInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }
/**
 * 手动切换的数据源, 调用DynamicDataSourceContextHolder.clear()清空当前线程的数据源信息,在Spring拦截器完成调用后清除。
 * @author chenxiangcai
 * @date 2022/6/16 09:59
 * @param request
 * @param response
 * @param handler
 * @param ex
 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DynamicDataSourceContextHolder.clear();
    }
}
