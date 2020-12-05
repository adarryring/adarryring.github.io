package io.github.darryring.util.exception;

import com.alibaba.fastjson.JSON;
import io.github.darryring.util.http.HttpStatusEnum;
import io.github.darryring.util.http.ResponseVO;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-21 16:37
 */

public class RestException extends RuntimeException {
    private static final long serialVersionUID = 2305201073664000499L;
    private int status;
    private String message;
    private Object data;

    public RestException(HttpStatusEnum httpStatus) {
        super(httpStatus.message());
        this.status = httpStatus.status();
        this.message = httpStatus.message();
    }

    public <T> RestException(HttpStatusEnum httpStatus, T data) {
        super(httpStatus.message());
        this.status = httpStatus.status();
        this.message = httpStatus.message();
        this.data = data;
    }

    public RestException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public <T> RestException(int status, String message, T data) {
        super(message);
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO<Object> toResponseVO() {
        return new ResponseVO(this.status, this.message, this.data);
    }

    public String toString() {
        return JSON.toJSONString(this.toResponseVO());
    }
}
