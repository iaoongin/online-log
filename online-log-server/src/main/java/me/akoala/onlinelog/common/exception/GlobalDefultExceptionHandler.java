package me.akoala.onlinelog.common.exception;

import com.ystmob.tech.common.api.ApiException;
import com.ystmob.tech.common.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常捕获
 */
@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handler(HttpServletRequest request, Exception e) {

//        StackTraceElement st = e.getStackTrace()[0];
//        String error = String.format("at %s.%s [%s] : %s", st.getClassName(),st.getMethodName(),  st.getLineNumber(), e.getMessage());
//        log.error(error);
        log.error(e.getMessage(), e);
        if (e instanceof BindException) {
            List<FieldError> fieldErrors = ((BindException) e).getFieldErrors();
            List<String> strings = fieldErrors.stream().map(fieldError -> fieldError.getField()).collect(Collectors.toList());
            return R.failed("参数格式有误" + strings);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException e1 = (HttpRequestMethodNotSupportedException) e;

            return R.failed(String.format("暂不支持 [%s] 请求方式.", e1.getMethod()));
        } else if (e instanceof ApiException) {

            ApiException apiException = (ApiException) e;
            if (apiException.getErrorCode() == null) {
                return R.failed(e.getMessage());
            }
            StackTraceElement trace1 = e.getStackTrace()[0];
            log.error(String.format("at %s.%s [%s].", trace1.getClassName(), trace1.getMethodName(), trace1.getLineNumber()));
            return R.restResult(null, apiException.getErrorCode());
        }
        return R.failed(e.getClass() + ": " + e.getMessage());
    }
}