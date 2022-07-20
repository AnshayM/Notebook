package pers.anshay.notebook.service.impl;

import pers.anshay.notebook.entity.DeptEntity;
import pers.anshay.notebook.mapper.DeptMapper;
import pers.anshay.notebook.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author anshay
 * @since 2022-07-20
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptEntity> implements IDeptService {

}
