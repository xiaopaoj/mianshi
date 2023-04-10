package org.mianshi.common.exception;

/**
 * @author : nate.jiang
 * @date : 2021/9/2 10:57
 */
public interface BaseEnum {
    /***
     * 编号
     * @return code
     */
    int getCode();

    /***
     * 详情
     * @return desc
     */
    String getDesc();
}
