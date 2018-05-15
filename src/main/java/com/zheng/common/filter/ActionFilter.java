package com.zheng.common.filter;

import com.zheng.common.util.IpUtil;
import com.zheng.logic.BigDataCollectionService;
import com.zheng.model.ActionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author zhengct on 2018/5/14
 */
public class ActionFilter implements Filter {

    private static Logger logger = LogManager.getLogger(ActionFilter.class.getName());
    private FilterConfig config;
    private WebApplicationContext context;
    private BigDataCollectionService bigDataCollectionService;

    public BigDataCollectionService getBigDataCollectionService() {
        return bigDataCollectionService;
    }

    public void setBigDataCollectionService(BigDataCollectionService bigDataCollectionService) {
        this.bigDataCollectionService = bigDataCollectionService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        if (bigDataCollectionService == null) {
            bigDataCollectionService = (BigDataCollectionService) context.getBean("bigDataCollectionServiceImpl");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        //筛选不过滤的请求
//        String excludes = config.getInitParameter("excludes");
//        request.setCharacterEncoding("UTF-8");
//        if (excludes != null) {
//            String[] strArray = excludes.split(";");
//            for (int i = 0; i < strArray.length; i++) {
//                if (strArray[i] == null || "".equals(strArray[i])) continue;
//                if (request.getRequestURI().indexOf(strArray[i]) != -1) {
//                    filterChain.doFilter(servletRequest, servletResponse);
//                    return;
//                }
//            }
//
//        }
//        String url = URLDecoder.decode(request.getRequestURL().toString(),"UTF-8");
//        String ip = IpUtil.getIpAddress(request);
//        ActionInfo actionInfo = new ActionInfo();
//        actionInfo.setIp(ip);
//        actionInfo.setUrl(url);
//        bigDataCollectionService.DataImport(actionInfo);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
