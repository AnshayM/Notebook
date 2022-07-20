package pers.anshay.notebook.service;

import pers.anshay.notebook.DaoException;
import pers.anshay.notebook.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.anshay.notebook.pojo.user.UserAddBo;
import pers.anshay.notebook.pojo.user.UserVo;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author anshay
 * @since 2022-07-19
 */
public interface IUserService extends IService<UserEntity> {

    boolean add(UserAddBo user) throws DaoException;

    List<UserVo> listUserInfo();

    boolean updateUser(UserAddBo user);

    UserEntity getByName(String name);
}
