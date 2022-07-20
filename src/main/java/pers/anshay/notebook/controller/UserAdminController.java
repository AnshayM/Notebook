package pers.anshay.notebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.notebook.service.IUserService;

import javax.annotation.Resource;

/**
 * @author machao
 * @date 2022/7/20
 */
@RestController
@RequestMapping("/user/admin")
public class UserAdminController {
    @Resource
    IUserService userService;

    @GetMapping("/listManage")
    public Object listManage() {
        return userService.list();
    }
}
