package cn.ihep.jdy.release.service.exception;

import cn.ihep.jdy.release.dao.model.ResultModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultModel defaultExceptionHandler(HttpServletResponse request, Exception e) throws Exception{
        ResultModel result;

        if (e.getClass() == BadCredentialsException.class) {
            result = new ResultModel(1002);
            return result;
        }

        if (e.getClass() == AccessDeniedException.class) {
            result = new ResultModel(1001);
            return result;
        }

        if (e.getClass() == MissingServletRequestParameterException.class||e.getClass() ==NullPointerException.class) {
            result = new ResultModel(1003);
            return result;
        }

        if (e.getMessage().equals("账号异常")) {
            result = new ResultModel(1004);
            return result;
        }

        result = new ResultModel(1000);
        return result;
    }

    @ExceptionHandler(value = CustomException.class)
    public ResultModel customExceptionHandler(HttpServletRequest request, CustomException e) throws Exception{
        Integer status = Integer.valueOf(e.getMessage());
        ResultModel result = new ResultModel(status);

        return result;
    }
}
