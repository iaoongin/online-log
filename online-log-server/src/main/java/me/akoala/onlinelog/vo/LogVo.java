package me.akoala.onlinelog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * LogVo
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/22 17:13
 */
@Data
@Accessors(chain = true)
public class LogVo {

    private String key;
    private String name;
    private List<String> lines;
    private String time;
}
