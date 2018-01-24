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
            customException=new CustomException("δ֪���쳣");
        }
        
        //�õ�������Ϣ
        String message =customException.getMessage();
        
        ModelAndView  modelAndView =new ModelAndView();
        modelAndView.addObject("message",message);
        //ָ�����ҳ��
        modelAndView.setViewName("error");
        
        return modelAndView;
    }

}


