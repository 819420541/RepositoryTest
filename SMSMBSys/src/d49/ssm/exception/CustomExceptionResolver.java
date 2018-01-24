package d49.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException customException =null;
        
        if(ex instanceof CustomException) {
            customException =(CustomException) ex;
        }else {
            customException=new CustomException("未知的异常");
        }
        
        //得到错误信息
        String message =customException.getMessage();
        
        ModelAndView  modelAndView =new ModelAndView();
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("error");
        
        return modelAndView;
    }

}


