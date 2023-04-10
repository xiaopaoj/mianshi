package org.mianshi.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : nate.jiang
 * @date : 2021/9/2 11:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    /***
     * 平台
     */
    private String platform;

    /***
     * 错误编码
     */
    private ServerCode serverCode;

    /***
     * 异常包含的附件
     */
    private Object attachment;

    /**
     * 异常信息描述
     */
    private String desc;

    public BaseException() {
    }

    public BaseException(String desc) {
        this.serverCode = ServerCode.INTERNAL_SERVER_ERROR;
        this.desc = desc;
    }

    public BaseException(ServerCode serverCode) {
        this.serverCode = serverCode;
        this.desc = serverCode.getDesc();
    }

    public BaseException(ServerCode serverCode, String desc) {
        this.serverCode = serverCode;
        this.desc = desc;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
