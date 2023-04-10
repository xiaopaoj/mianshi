package org.mianshi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.mianshi.common.exception.ServerCode;

import java.io.Serializable;

/**
 * @author : nate.jiang
 * @date : 2019/12/26 10:19
 */
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -8145865776285690954L;
    //常量 默认状态码code：200
    public static final int DEFAULT_STATUS_CODE = 200;
    //常量 默认描述success
    public static final String DEFAULT_MESSAGE = "success";

    //业务异常常量 默认状态码code400
    public static final int ERROR_BUSINESS_STATUS_CODE = 400;
    //系统内部异常常量 默认状态码code500
    public static final int ERROR_SYSTEM_STATUS_CODE = 500;
    //身份认证失败
    public static final int ERROR_AUTH_FAILD_STATUS_CODE = 401;

    private int code;
    private String message;
    //返回内容
    private T data;
    /**
     * 分页信息 //注解不返回null值字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse page;

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }

    public ResponseResult(int code, String message, T data, PageResponse pageResponse) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageResponse;
    }

    public ResponseResult(int code, String message, T data, Long total, Integer currentPage, Integer pageSize) {
        PageResponse pageResponse = new PageResponse(total, currentPage, pageSize);
        this.code = code;
        this.message = message;
        this.data = data;
        this.page = pageResponse;
    }

    /**
     * 分页返回数据方法
     *
     * @param data        数据集
     * @param total       总条数
     * @param currentPage 当前页
     * @param pageSize    每页的数量大小
     * @return
     */
    public static <T> ResponseResult successPage(T data, Long total, Integer currentPage, Integer pageSize) {
        PageResponse pageResponse = new PageResponse(total, currentPage, pageSize);
        return new ResponseResult(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE, data, pageResponse);
    }

    //无参构造函数
    public ResponseResult() {
    }

    //有参构造函数
    public ResponseResult(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 内部 自定义返无参数回结果的静态方法
     *
     * @return
     */
    public static ResponseVOBuilder response() {
        return new ResponseVOBuilder();
    }

    /**
     * 内部 自定义 有参数返回结果的静态方法
     *
     * @param status_code
     * @param message
     * @return
     */
    public static ResponseVOBuilder response(int status_code, String message) {
        return new ResponseVOBuilder(status_code, message);
    }

    /**
     * 返回成功带状态码、描述的静态方法
     *
     * @return
     */
    public static ResponseResult success() {
        return response(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE).build();
    }


    /**
     * 返回错误状态码、描述的静态方法
     *
     * @return
     */
    public static ResponseResult error(int code, String message) {
        ResponseResult restResponse = new ResponseResult(code, message);
        return restResponse;
    }

    public static ResponseResult error(ServerCode serverCode) {
        ResponseResult restResponse = new ResponseResult(serverCode.getCode(), serverCode.getDesc());
        return restResponse;
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 返回带状态码、描述、数据的静态方法
     *
     * @param data
     * @return
     */
    public static<T> ResponseResult<T> success(T data) {
        return response(DEFAULT_STATUS_CODE, DEFAULT_MESSAGE).setData(data).build();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //静态类
    public static class ResponseVOBuilder {
        private int code = DEFAULT_STATUS_CODE;
        private String message = DEFAULT_MESSAGE;
        private Object data;

        //无参构造函数
        public ResponseVOBuilder() {
            super();
        }

        //有参构造函数
        public ResponseVOBuilder(int code, String message) {
            super();
            this.code = code;
            this.message = message;
        }

        public ResponseVOBuilder setCode(int code) {
            this.code = code;
            return this;
        }

        public ResponseVOBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseVOBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        /**
         * 返回带状态码、描述、数据的方法
         *
         * @return
         */
        public ResponseResult build() {
            return new ResponseResult(code, message, data);
        }
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + data +
                '}';
    }

}
