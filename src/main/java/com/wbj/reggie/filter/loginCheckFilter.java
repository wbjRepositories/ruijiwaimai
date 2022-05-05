package com.wbj.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.wbj.reggie.common.BaseContext;
import com.wbj.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class loginCheckFilter implements Filter {
    //路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();

        log.info("已拦截请求：{}",requestURI);

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        boolean check = check(urls, requestURI);
        if (check){
            log.info("请求{}不需要处理",requestURI);
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        if (httpServletRequest.getSession().getAttribute("employee") != null){
            Long employeeId = (Long) httpServletRequest.getSession().getAttribute("employee");
            log.info("请求用户已登录，id：{}",employeeId);
            BaseContext.setCurrentId(employeeId);
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        log.info("用户未登录");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
//        log.info("拦截到请求: {}",httpServletRequest.getRequestURI());
//        chain.doFilter(httpServletRequest,httpServletResponse);
    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
