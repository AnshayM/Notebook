package pers.anshay.notebook;

import lombok.Getter;
import pers.anshay.notebook.common.enums.ReturnCodeEnum;

/**
 * @author machao
 * @date 2022/7/20
 */
public class DaoException extends Exception {
    //todo 补充code
    @Getter
    private ReturnCodeEnum codeEnum;

    private Integer code;


    public DaoException(String message) {
        super(message);
    }
}
