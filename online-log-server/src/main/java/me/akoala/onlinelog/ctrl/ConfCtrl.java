package me.akoala.onlinelog.ctrl;

import com.ystmob.tech.common.api.ApiController;
import com.ystmob.tech.common.api.R;
import me.akoala.onlinelog.prop.OnlineLogProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ConfCtrl
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/22 15:14
 */
@RestController
@RequestMapping("/conf")
public class ConfCtrl extends ApiController {

    @Autowired
    private OnlineLogProp onlineLogProp;

    @GetMapping("prop")
    public R<OnlineLogProp> getProp(){
        return success(onlineLogProp);
    }

}
