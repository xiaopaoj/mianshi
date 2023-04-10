package org.mianshi.service;

import java.util.List;

/**
 * @author ：nate.jiang
 * @date ：2023/4/10 13:21
 */
public interface BaseService<T> {

    /**
     * 插入数据
     * @param record 数据
     * @return  影响条数
     */
     int insert(T record);

    /**
     * 插入不为空的数据
     * @param record 数据
     * @return  影响条数
     */
    int insertSelective(T record);

    /**
     * 通过主键ID，更新数据
     * @param record 数据
     * @return  影响条数
     */
    //int updateByPrimaryKey(T record);

    /**
     * 通过主键ID，更新不为空的字段
     * @param record 数据
     * @return  影响条数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 数据更新，根据条件更新
     * @param record 数据
     * @param example 条件
     * @return  影响条数
     */
    //int updateByExample(T record, Object example);

    /**
     * 根据条件更新不为空的数据字段
     * @param record 数据
     * @param example 条件
     * @return  影响条数
     */
    int updateByExampleSelective(T record, Object example);

    /**
     * 删除数据
     * @param record 数据
     * @return  影响条数
     */
    int delete(T record);

    /**
     * 通过主键ID，删除数据
     * @param record 数据
     * @return  影响条数
     */
    int deleteByPrimaryKey(T record);

    /**
     * 通过查询条件查询数据列表
     * @param record 数据
     * @return  数据列表
     */
    List<T> select(T record);

    /**
     * 通过Example条件查询数据列表
     * @param o 查询条件
     * @return  数据列表
     */
    List<T> selectByExample(Object o);

    /**
     * 通过主键ID查询数据
     * @param o 主键
     * @return  数据
     */
    T selectByPrimaryKey(Object o);

    /**
     * 通过主键ID查询数据
     * @param o 主键
     * @return  数据
     */
    T selectByPrimaryKeyAndDelFlag(Class<T> tClass, Object id, Object delFlag);

    /**
     * 通过条件查询一条
     * @param o 主键
     * @return  数据
     */
    T selectOneByExample(Object o);


    /**
     * 通过Example条件统计数据数量
     * @param o Example
     * @return  数量
     */
    int selectCountByExample(Object o);

    /**
     * 条件统计数据数量
     * @param record 条件
     * @return 数量
     */
    int selectCount(T record);

}
