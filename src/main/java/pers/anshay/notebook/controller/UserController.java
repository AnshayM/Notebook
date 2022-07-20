package pers.anshay.notebook.controller;


import org.springframework.web.bind.annotation.*;
import pers.anshay.notebook.DaoException;
import pers.anshay.notebook.pojo.user.UserAddBo;
import pers.anshay.notebook.service.IUserService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author anshay
 * @since 2022-07-19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    @GetMapping("/list")
    public Object listUsers() {
        return userService.listUserInfo();
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteUser(@PathVariable("id") @NotNull Long id) {
        return userService.removeById(id);
    }

    @PostMapping("/add")
    public Object addUser(@RequestBody UserAddBo user) throws DaoException {
        return userService.add(user);
    }

    @PostMapping("/update")
    public Object updateUser(@RequestBody UserAddBo user) {
        return userService.updateUser(user);
    }
}

