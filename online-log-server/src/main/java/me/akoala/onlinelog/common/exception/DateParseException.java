package me.akoala.onlinelog.common.exception;

/**
 * <p>
 * 自定义日期解析异常
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/10/11 10:59
 */
public class DateParseException extends NotFoundException {

    public DateParseException(String date) {
        super("date:" + date + ", parse error.");
    }

    public DateParseException(String date, Throwable cause) {
        super("date:" + date + ", parse error.", cause);
    }

    protected DateParseException(String date, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("date:" + date + ", parse error.", cause, enableSuppression, writableStackTrace);
    }
}
