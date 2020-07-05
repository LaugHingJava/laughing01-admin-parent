package com.laughing.crowd.config;

import com.google.gson.Gson;
import com.laughing.crowd.constant.CrowdConstant;
import com.laughing.crowd.exception.DeleteAnomalyException;
import com.laughing.crowd.exception.LoginFailedException;
import com.laughing.crowd.exception.ModifyAnomalyException;
import com.laughing.crowd.exception.SaveAnomalyException;
import com.laughing.crowd.util.CrowdUtil;
import com.laughing.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//配置全局异常处理类
@ControllerAdvice
public class CrowdExceptionResolver {
    //修改时异常跳转这个页面
    @ExceptionHandler(value = ModifyAnomalyException.class)
    public ModelAndView ModifyAnomalyException(ModifyAnomalyException exception,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "admin-modify";
        return commonResolver(viesName,exception,request,response);
    }
    //删除时异常跳转这个页面
    @ExceptionHandler(value = DeleteAnomalyException.class)
    public ModelAndView DeleteAnomalyException(DeleteAnomalyException exception,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "system-error";
        return commonResolver(viesName,exception,request,response);
    }
    //增加时异常跳转这个页面
    @ExceptionHandler(value = SaveAnomalyException.class)
    public ModelAndView SaveAnomalyException(SaveAnomalyException exception,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "admin-add";
        return commonResolver(viesName,exception,request,response);
    }
    //发生运行时异常跳转这个页面
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView resolveException(RuntimeException exception,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "system-error";
        return commonResolver(viesName,exception,request,response);
    }


    //发生登录时异常跳转这个页面
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView LoginFailedException(LoginFailedException exception,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viesName = "admin-login";
        return commonResolver(viesName,exception,request,response);
    }

    private ModelAndView commonResolver(String viewName, Exception excpetion,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(CrowdUtil.judgeRequestType(request)){//判断是否ajax请求
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
