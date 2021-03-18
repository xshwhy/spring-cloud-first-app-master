package com.fayfox.dto;

import com.fayfox.exception.validation.Result;

import java.io.Serializable;

public class RespDTO<T> implements Serializable {
    private static final long serialVersionUID = 3576198213878229564L;

    private int code;
    private T data;
    private String msg;
    private String alert;

    public RespDTO(int code, String msg, T data, String alert) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.alert = alert;
    }

    /**
     * 返回数据
     * @return RespDTO
     */
    public static RespDTO<Object> success(Object data) {
        return new RespDTO<>(0, "", data, "");
    }

    /**
     * 单纯返回一个消息
     * @return RespDTO
     */
    public static RespDTO<Object> msg(String msg) {
        return new RespDTO<>(0, msg, null, "");
    }

    /**
     * 弹出警告（需要客户端支持）
     */
    public static RespDTO<Object> alert(String alert, String msg) {
        return alert(alert, msg, 0);
    }

    /**
     * 弹出警告（需要客户端支持）
     */
    public static RespDTO<Object> alert(String alert, String msg, int code) {
        return new RespDTO<>(code, msg, null, alert);
    }

    /**
     * 返回报错
     */
    public static RespDTO<Object> error(String msg, int code) {
        return new RespDTO<>(code, msg, null, "");
    }

    /**
     * 返回报错
     */
    public static RespDTO<Object> error(String msg) {
        return error(msg, 10000);
    }

    /**
     * 表单验证报错
     */
    public static RespDTO<Object> validateError(Result result) {
        return new RespDTO<>(10002, "", result, "");
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", alert='" + alert + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}

