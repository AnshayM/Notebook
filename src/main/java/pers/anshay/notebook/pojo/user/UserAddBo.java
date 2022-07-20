package pers.anshay.notebook.pojo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author machao
 * @date 2022/7/19
 */
@Data
public class UserAddBo {

    /**
     * 名称
     */
    @NotNull(message = "用户名不可为空")
    private String name;

    /**
     * 密码
     */
    @NotNull(message = "密码不可为空")
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

}
