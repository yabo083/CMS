package com.yabo.cms.entity.Response;


public enum ReturnCode {
    RC200(200, "ok！"),
    RC400(400, "请求失败，参数错误，请检查后重试！"),
    RC404(404, "未找到您请求的资源！"),
    RC405(405, "请求方式错误，请检查后重试！"),
    RC500(500, "操作失败，服务器繁忙或服务器错误，请稍后再试！");

    // 自定义状态码
    private final int code;

    // 自定义描述
    private final String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
