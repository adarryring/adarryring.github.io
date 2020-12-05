package io.github.darryring.util.responce;

import lombok.ToString;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author darryring
 */
@ToString
public class ResVO extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ResVO() {
        put("code", 200);
        put("msg", "success");
        put("success", "YES");
    }

    public static ResVO error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static ResVO error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResVO error(int code, String msg) {
        ResVO resVO = new ResVO();
        resVO.put("code", code);
        resVO.put("success", "NO");
        resVO.put("msg", msg);
        return resVO;
    }

    public static ResVO ok(String msg) {
        ResVO resVO = new ResVO();
        resVO.put("msg", msg);
        return resVO;
    }

    public static ResVO ok(Map<String, Object> map) {
        ResVO resVO = new ResVO();
        resVO.putAll(map);
        return resVO;
    }

    public static ResVO ok() {
        return new ResVO();
    }

    @Override
    public ResVO put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
