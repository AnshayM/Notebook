package pers.anshay.notebook.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.anshay.notebook.DaoException;
import pers.anshay.notebook.entity.UserEntity;
import pers.anshay.notebook.mapper.UserMapper;
import pers.anshay.notebook.pojo.user.UserAddBo;
import pers.anshay.notebook.pojo.user.UserVo;
import pers.anshay.notebook.service.IUserService;

import java.util.List;

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
    public boolean add(UserAddBo user) throws DaoException {
        UserEntity oldUser = getByName(user.getName());
        if (oldUser != null) {
            throw new DaoException("数据重复");
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        return save(entity);
    }

    @Override
    public List<UserVo> listUserInfo() {
        List<UserEntity> list = list();
        // 暂时不考虑性能问题
        return JSON.parseArray(JSON.toJSONString(list), UserVo.class);
    }

    @Override
    public boolean updateUser(UserAddBo user) {
        UserEntity entity = JSON.parseObject(JSON.toJSONString(user), UserEntity.class);
        return updateById(entity);
    }

    @Override
    public UserEntity getByName(String name) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>().eq(UserEntity::getName, name);
        return getOne(wrapper);
    }
}
