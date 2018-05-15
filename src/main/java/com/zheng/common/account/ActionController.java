package com.zheng.common.account;

import com.zheng.common.util.IpUtil;
import com.zheng.logic.BigDataCollectionService;
import com.zheng.model.ActionInfo;
import com.zheng.model.OperResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengct on 2018/5/15
 */
@Controller
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BigDataCollectionService bigDataCollectionService;

    @RequestMapping("/ip")
    @ResponseBody
    public OperResult getIp(){
        String ipAddress = IpUtil.getIpAddress(request);
        HashMap<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("ip",ipAddress);
        OperResult or  = new OperResult();
        or.setData(resultMap);
        or.setStatus(OperResult.OPER_SUCCESS);
        or.setMsg("成功");
        return or;
    }

    @RequestMapping("/actionInfo")
    @ResponseBody
    public String getAction(ActionInfo actionInfo){
        bigDataCollectionService.DataImport(actionInfo);
        return "success";
    }
}
