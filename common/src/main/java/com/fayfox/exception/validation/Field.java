package com.fayfox.exception.validation;

public class Field {
    /**
     * 验证规则
     */
    private String code;

    /**
     * 字段名
     */
    private String name;

    /**
     * 报错信息
     */
    private String msg;

    public Field(String code, String name, String msg) {
        this.code = code;
        this.name = name;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
