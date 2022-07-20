package pers.anshay.notebook.service;

import pers.anshay.notebook.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.anshay.notebook.pojo.user.UserAddBo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author anshay
 * @since 2022-07-19
 */
public interface IUserService extends IService<UserEntity> {

    Object add(UserAddBo user);
}
