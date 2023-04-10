package org.mianshi.common.exception;


/**
 * @author : nate.jiang
 * @date : 2021/9/2 10:57
 */
public enum ServerCode implements BaseEnum {

    /**
     * */
    SUCCESS(200, "success"),

    INVALID_REQUEST(400, "参数类型非法"),

    UNAUTHORIZED(401, "未登录"),

    UNKNOWN_ACCOUNT(401,"账号或密码不正确"),

    MOBILE_CODE_VAILD_FAIL(402,"短信验证码错误"),

    APP_VERSION_NOT_NEW(403,"app版本不是最新的"),

    NOT_FOUND(404, "请求路径未找到"),

    HTTP_MESSAGE_NOT_READABLE(-410, "HTTP消息不可读"),

    INTERNAL_SERVER_ERROR(500, "服务器发生错误"),

    ILLEGAL_ARGUMENT_ERROR(-10003,"参数非法"),

    WRONG_PARAM(-10009,"请求参数错误"),

    ;

    private int code;

    private String desc;

    ServerCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {

        return this.code;
    }

    @Override
    public String getDesc() {

        return this.desc;
    }
}
