package me.akoala.onlinelog.common.exception;

/**
 * <p>
 * 股票代码未找到异常
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/10/11 10:59
 */
public class SymbolNotFoundException extends NotFoundException {

    public SymbolNotFoundException(String symbol) {
        super("symbol:" + symbol + ", not found.");
    }

    public SymbolNotFoundException(String symbol, Throwable cause) {
        super("symbol:" + symbol + ", not found.", cause);
    }

    protected SymbolNotFoundException(String symbol, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("symbol:" + symbol + ", not found.", cause, enableSuppression, writableStackTrace);
    }
}
