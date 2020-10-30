package anshay.notebook.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author machao
 * @date 2020/10/14
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @ApiOperation("测试")
    @GetMapping("/test")
    public Object test() {
        return "测试成功";
    }
}
