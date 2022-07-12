package pers.anshay.notebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.notebook.config.LocalConfig;
import pers.anshay.notebook.config.LocalUserConfig;

import javax.annotation.Resource;

/**
 * 查询配置信息
 *
 * @author machao
 * @date 2022/7/12
 */
@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {
    @Resource
    private LocalUserConfig localUserConfig;
    @Resource
    private LocalConfig localConfig;


    @GetMapping("/get-local-user")
    public Object getLocalUser() {
        return localUserConfig.toString();
    }

    @GetMapping("/get-local-config")
    public Object getLocalConfig() {
        return localConfig.toString();
    }
}
