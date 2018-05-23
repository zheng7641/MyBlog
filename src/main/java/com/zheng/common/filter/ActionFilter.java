package com.zheng.common.filter;

import com.zheng.common.util.IpUtil;
import com.zheng.logic.BigDataCollectionService;
import com.zheng.model.ActionInfo;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author zhengct on 2018/5/14
 */
public class ActionFilter implements Filter {

    private static Logger logger = Logger.getLogger(ActionFilter.class.getName());
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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        //筛选不过滤的请求
        String excludes = config.getInitParameter("excludes");
        request.setCharacterEncoding("UTF-8");
        if (excludes != null) {
            String[] strArray = excludes.split(";");
            for (int i = 0; i < strArray.length; i++) {
                if (strArray[i] == null || "".equals(strArray[i])) continue;
                if (request.getRequestURI().indexOf(strArray[i]) != -1) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }

        }
        String url = URLDecoder.decode(request.getRequestURL().toString(),"UTF-8");
        String ip = IpUtil.getIpAddress(request);
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.setIp(ip);
        actionInfo.setUrl(url);
        actionInfo.setOptDate(new Date());

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        if(userAgent.getBrowser().getName().equals("Robot/Spider")){
            Browser browser = userAgent.getBrowser();
            Version browserVersion = userAgent.getBrowserVersion();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            actionInfo.setBrowserName(browser.getName());
            actionInfo.setBrowserVer(browserVersion==null?"":browserVersion.getVersion());
            actionInfo.setManufacturer(browser.getManufacturer().getName());
            actionInfo.setClient(operatingSystem.getDeviceType().getName());
            actionInfo.setOsName(operatingSystem.getName());
            bigDataCollectionService.DataImport(actionInfo);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
