package pers.anshay.notebook.common.enums;

import lombok.Getter;

/**
 * @author machao
 * @date 2022/7/20
 */
public enum ReturnCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0L, "success"),

    //=============================2开头返回码===============================================
    /**
     * 非法入参 一般格式校验失败
     * -2xxxx 请求参数
     */
    INVALID_PARAM(20000L, "非法入参"),
    /**
     * 参数异常
     */
    PARAM_ERROR(20001L, "参数异常"),

    /**
     * 参数不能为空
     */
    PARAM_CANNOT_BE_EMPTY(20002L, "参数不能为空"),

    //=============================3开头返回码===============================================
    /**
     * 调用外部系统异常
     * 3xxxx
     */
    REMOTE_CALL_ERROR(30000L, "调用外部系统异常"),
    TEACH_ERROR(300001L, "创建教育组件出错 !"),
    REMOTE_CALL_TIMEOUT(300002L, "调用外部服务超时"),
    PORTRAIT_EXTRACTION_ERROR(300003L, "创建用户画像出错！"),

    //=============================4开头返回码===============================================
    /**
     * 系统内部异常
     * 4xxxx
     */
    SYSTEM_ERROR(40000L, "系统内部异常"),

    /**
     * SQL执行异常
     * 4xxxx
     */
    SQL_ERROR(40001L, "SQL执行异常"),

    /**
     * 超出流量限制
     */
    FLOW_LIMIT_EXCEEDED(50000L, "超过流量限制"),


    //=============================9开头自定义异常返回码===============================================
    /**
     * 默认错误
     */
    DEFAULT_ERROR(99999L, "默认错误"),
    /**
     *
     */
    DELETE_CATEGORY_ERROR(90001L, "有关联数据,不允许删除"),
    /**
     *
     */
    DELETE_CHILD_ERROR(90004L, "有关联子级,不允许删除"),
    /**
     *
     */
    NAME_RRPEAT_ERROR(90005L, "名称重复,操作失败"),
    /**
     *
     */
    KEY_RRPEAT_ERROR(90010L, "key重复,操作失败"),
    /**
     *
     */
    DELETE_RULE_ON_LINE_ERROR(90003L, "已上线,不允许删除"),
    /**
     *
     */
    NOT_ON_LINE_ERROR(90006L, "数据错误,不允许上线"),
    /**
     *
     */
    ANSWER_NOT_NULL_ERROR(90008L, "话术推荐问答不能为空,操作失败"),
    /**
     *
     */
    NOT_ON_LINE_ERROR_TEMPLATE(90007L, "小结模板下缺少字段,不允许上线"),
    /**
     *
     */
    ADD_DATA_MAX_SIZE_ERROR(90009L, "数据添加个数不能超过上限"),
    /**
     * 数据库查询的记录不存在
     */
    DATA_NOT_FOUND(90002L, "数据不存在");

    private Long code;

    @Getter
    private String message;

    ReturnCodeEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }
}
