package cn.mrbcy.sound.advice;

import cn.mrbcy.sound.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Yang on 2018/7/7.
 */
@RestControllerAdvice
public class ExceptionAdvisor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exceptionHandler(Exception e) {
        logger.error("ExceptionAdvisor error: " + e.getStackTrace().toString());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器错误，请等候管理员处理");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result formatCheckExceptionHandler(RuntimeException e) {
        return new Result(HttpStatus.BAD_REQUEST.value(), "服务器错误，请等候管理员处理");
    }

}
