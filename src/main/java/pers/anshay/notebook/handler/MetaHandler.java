package pers.anshay.notebook.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author machao
 * @date 2022/7/20
 */
@Component
public class MetaHandler implements MetaObjectHandler{
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATED_TIME = "createTime";
    private static final String CREATOR_ID = "creatorId";

    @Override
    public void insertFill(MetaObject metaObject) {
        // this.strictInsertFill(metaObject, CREATOR_ID, Long.class, getCurrentUserId());
        this.strictInsertFill(metaObject, CREATED_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        // 重写原生方法，添加一个判断是否更新时间
        if (UPDATE_TIME.equals(fieldName) || metaObject.getValue(fieldName) == null) {
            Object obj = fieldVal.get();
            if (Objects.nonNull(obj)) {
                metaObject.setValue(fieldName, obj);
            }
        }
        return this;
    }

    /**
     * 获取当前登陆用户的id
     *
     * @return userId
     */
    /*private Long getCurrentUserId() {
        if (ValidateUtil.isEmpty(UserContext.getUserVO())) {
            return 0L;
        } else {
            return UserContext.getUserVO().getUserId();
        }
    }*/
}
