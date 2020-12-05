package io.github.darryring.util.http;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-21 16:35
 */

public class ResponseVO<T> {
    private int status;
    private String message;
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(HttpStatusEnum httpStatus) {
        this.status = httpStatus.status();
        this.message = httpStatus.message();
    }

    public ResponseVO(HttpStatusEnum httpStatus, T data) {
        this.status = httpStatus.status();
        this.message = httpStatus.message();
        this.data = data;
    }

    public ResponseVO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseVO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(T data) {
        this.status = HttpStatusEnum.SUCCESS.status();
        this.message = HttpStatusEnum.SUCCESS.message();
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
