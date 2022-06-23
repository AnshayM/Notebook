package pers.anshay.notebook.controller;

import pers.anshay.notebook.test.YugaoSubscribe;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author machao
 * @date 2020/10/14
 */
@Slf4j
@Api(tags = "测试")
@RestController
public class TestController {

    @ApiOperation("测试op")
    @GetMapping("/test")
    public Object test() {
        return "测试成功";
    }

    @ApiOperation("测试op2")
    @PostMapping("/testOp2")
    public Object testOp2(@RequestBody String param) {
        log.info("param:{}", param);
        return "获取参数是" + param;
    }

    @ApiOperation("测试nofmsg")
    @PostMapping(value = "/dtb/v1/nofmsg", produces = "application/json;charset=UTF-8")
    public Object nofmsg(@RequestBody YugaoSubscribe param) {
        log.info("param:{}", JSON.toJSONString(param));
        return "获取参数是" + param;
    }
}
