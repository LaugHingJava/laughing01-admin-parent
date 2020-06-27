package com.laughing.crowd.config;

import com.google.gson.Gson;
import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.util.CrowdReqUtil;
import com.laughing.crowd.util.ResultEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//配置全局异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {
    //发生异常跳转这个页面
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView resolveException(Exception exception,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "system-error";
        return commonResolver(viesName,exception,request,response);
    }

    private ModelAndView commonResolver(String viewName, Exception excpetion,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(CrowdReqUtil.judgeRequestType(request)){//判断是否ajax请求
            ResultEntity<Object> resultEntity = new ResultEntity<Object>("failed",excpetion.getMessage(),null);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(resultEntity));
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,excpetion);
        modelAndView.setViewName(viewName);
        return modelAndView;


    }
}
