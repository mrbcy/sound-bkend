package cn.mrbcy.sound.advice;

import cn.mrbcy.sound.domain.Result;
import cn.mrbcy.sound.exception.UnauthorizedException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yang on 2018/7/7.
 */
@RestControllerAdvice
public class ExceptionAdvisor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //无权限访问
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    public Result handleForbidden(){
        return new Result(HttpStatus.FORBIDDEN.value(),
                "You don't have the corresponding permissions. Please login and try again.", null);
    }

    //无权限访问
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Result handleAuthenticationException(AuthenticationException e){
        return new Result(HttpStatus.UNAUTHORIZED.value(),e.getMessage(), null);
    }

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return new Result(401, "Unauthorized, Please login and try again.", null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401(UnauthorizedException e) {
        return new Result(401, e.getMessage(), null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        return new Result(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null || statusCode == 200) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.valueOf(statusCode);
    }



}
