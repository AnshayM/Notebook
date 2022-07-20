package pers.anshay.notebook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.anshay.notebook.entity.UserEntity;
import pers.anshay.notebook.mapper.UserMapper;
import pers.anshay.notebook.pojo.user.UserAddBo;
import pers.anshay.notebook.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author anshay
 * @since 2022-07-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public Object add(UserAddBo user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        return save(entity);
    }
}
