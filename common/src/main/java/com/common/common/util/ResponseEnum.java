package com.common.common.util;

public enum ResponseEnum {
    success(200, "操作成功"),
    closure(301, "被封禁"),
    error(400, "很抱歉,系统发生故障"),
    please_login(401, "请重新登录"),
    client_error_request(415, "非法请求"),
    obj_not_exist(404, "对象不存在"),
    client_request_timeout(408, "客户端请求超时"),
    param_error(414, "参数错误"),
    failure(500, "操作失败"),
    verson(504, "请升级版本"),
    imei_error(505, "设备不在白名单内"),
    server_busy(503, "服务器繁忙");

    private int code;
    private String message;

    private ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
