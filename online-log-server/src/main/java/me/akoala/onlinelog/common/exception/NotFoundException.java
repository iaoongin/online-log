package me.akoala.onlinelog.common.exception;

/**
 * <p>
 * 未找到异常
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/10/11 10:58
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
