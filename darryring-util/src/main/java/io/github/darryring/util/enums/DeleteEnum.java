package io.github.darryring.util.enums;

import lombok.Getter;

/**
 * @author darryring
 */
@Getter
public enum DeleteEnum {
    /**
     * 未删除
     */
    NOT_DEL(0),
    /**
     * 已删除
     */
    DELETED(1);

    private Integer status;

    DeleteEnum(Integer status) {
        this.status = status;
    }

}
