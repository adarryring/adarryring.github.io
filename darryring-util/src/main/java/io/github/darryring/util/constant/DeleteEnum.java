package io.github.darryring.util.constant;

import lombok.Getter;

/**
 * @author darryring
 */
@Getter
public enum DeleteEnum {
    /**
     * 有效
     */
    NOT_DEL(0),
    /**
     * 删除
     */
    IS_DEL(1);
    private Integer status;

    DeleteEnum(Integer status){
        this.status=status;
    }

}
