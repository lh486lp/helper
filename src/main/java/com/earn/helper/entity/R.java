package com.earn.helper.entity;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class R<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", "1");
        put("msg", "成功");
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error() {
        return error("0", "失败");
    }

    public static R error(String msg) {
        return error("0", msg);
    }

    public static R error(String code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R setData(T data) {
        super.put("data", data);
        return this;
    }
}
