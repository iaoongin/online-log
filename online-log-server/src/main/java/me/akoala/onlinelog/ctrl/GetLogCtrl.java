package me.akoala.onlinelog.ctrl;

import cn.hutool.core.date.DateUtil;
import com.ystmob.tech.common.api.ApiController;
import com.ystmob.tech.common.api.R;
import lombok.extern.slf4j.Slf4j;
import me.akoala.onlinelog.prop.OnlineLogProp;
import me.akoala.onlinelog.util.TailUtils;
import me.akoala.onlinelog.vo.LogVo;
import me.akoala.onlinelog.vo.NameVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * GetLogCtrl
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/22 17:12
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class GetLogCtrl extends ApiController {

    @Resource
    private OnlineLogProp onlineLogProp;
/*
    @GetMapping("tail")
    public R<List<LogVo>> tail(@RequestParam(required = false, defaultValue = "3") Integer num) {

        Map<String, OnlineLogProp.Log> logMap = onlineLogProp.getLogMap();

        List<LogVo> result = logMap.keySet().stream().map(key -> getLogVo(num, logMap, key)).collect(Collectors.toList());

        return success(result);
    }*/

    @GetMapping("names")
    public R<List<NameVo>> getNames() {
        Map<String, OnlineLogProp.Log> logMap = onlineLogProp.getLogMap();
        List<NameVo> result = logMap.keySet().stream().map(k -> NameVo.builder()
                .key(k)
                .name(logMap.get(k).getName())
                .build())
                .collect(Collectors.toList());
        return success(result);
    }

    @GetMapping("tail")
    public R<List<LogVo>> tail(@RequestParam(required = false, defaultValue = "") String key, @RequestParam(required = false, defaultValue = "3") Integer num) {

        Map<String, OnlineLogProp.Log> logMap = onlineLogProp.getLogMap();

        List<LogVo> result;
        if (StringUtils.hasText(key)) {
            OnlineLogProp.Log log = logMap.get(key);
            if (Objects.isNull(log)) {
                return success(null);
            } else {
                result = new ArrayList<>();
                result.add(getLogVo(num, logMap, key));
            }
        } else {
            result = logMap.keySet().stream().map(k -> getLogVo(num, logMap, k)).collect(Collectors.toList());
        }

        log.info(DateUtil.now());

        return success(result);
    }

    private LogVo getLogVo(Integer num, Map<String, OnlineLogProp.Log> logMap, String key) {
        OnlineLogProp.Log log = logMap.get(key);
        String name = log.getName();
        String path = log.getPath();
        List<String> tail = TailUtils.tail(path, num);
        return new LogVo().setKey(key).setName(name).setLines(tail).setTime(DateUtil.now());
    }


}
