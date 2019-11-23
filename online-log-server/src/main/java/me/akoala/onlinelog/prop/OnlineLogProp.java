package me.akoala.onlinelog.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * LogProp
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/22 15:09
 */
@Component
@ConfigurationProperties(prefix="online.log")
@Data
public class OnlineLogProp {

    private Map<String, Log> logMap;
    private String test;

    @Data
    public static class Log {
        private String name;
        private String path;
    }
}
