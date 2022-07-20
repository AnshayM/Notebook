package pers.anshay.notebook.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import pers.anshay.notebook.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author anshay
 * @since 2022-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dept")
public class DeptEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门
     */
    private Integer parent;


}
