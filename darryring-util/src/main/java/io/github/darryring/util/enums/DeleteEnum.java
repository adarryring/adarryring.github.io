package io.github.darryring.util.enums;

import lombok.Getter;

/**
 * @author darryring
 */
@Getter
public enum DeleteEnum {

    NOT_DEL(0),
    DELETED(1);

    private Integer status;

    DeleteEnum(Integer status) {
        this.status = status;
    }

}
