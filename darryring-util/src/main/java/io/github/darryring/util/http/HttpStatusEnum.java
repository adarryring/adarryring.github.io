package io.github.darryring.util.http;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-21 16:35
 */

public enum HttpStatusEnum {
    SUCCESS(0, "success"),
    BAD_REQUEST(20400, "请求错误"),
    UNAUTHORIZED(20401, "鉴权失败"),
    NOT_FOUND(20404, "资源没有找到"),
    METHOD_NOT_ALLOWED(20405, "请求方法不支持"),
    NOT_ACCEPTABLE(20406, "请求不接受"),
    CONFLICT(20409, "校验数据失败"),
    LOCKED(20423, "资源被锁定"),
    INTERNAL_SERVER_ERROR(20500, "服务器错误"),
    BAD_GATEWAY(20502, "网关错误"),
    SERVICE_UNAVAILABLE(20503, "服务不可用"),
    ACCOUNT_NOTFOUND(20701, "帐号或密码错误"),
    ACCOUNT_INVALID(20702, "帐号异常"),
    ACCOUNT_SUSPEND(20703, "帐号不可用"),
    PASSWORD_INCORRECT(20704, "帐号或密码错误"),
    ACCOUNT_ALREADY_EXIST(20705, "帐号已存在"),
    CAPTCHA_TYPE_ERROR(20601, "验证码类型错误"),
    CAPTCHA_ERROR(20602, "验证码错误"),
    CAPTCHA_INVALID(20603, "验证码失效"),
    CAPTCHA_OVER_LIMIT(20604, "验证码验证次数超过限制"),
    CLIENTID_EMPTY(20801, "clientId为空"),
    TOKEN_EMPTY(20802, "token为空"),
    TIMESTAMP_INVALID(20803, "非法的时间戳"),
    CLIENTID_NOTFOUND(20804, "找不到clientId"),
    TOKEN_INCORRECT(20805, "token错误");

    private final int status;
    private final String message;

    private HttpStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }

    public String toString() {
        return StringUtils.join(new Serializable[]{this.status, ": ", this.message});
    }
}
