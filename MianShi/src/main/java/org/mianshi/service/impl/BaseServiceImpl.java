package org.mianshi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mianshi.service.BaseService;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ：nate.jiang
 * @date ：2023/4/10 13:21
 */
@Slf4j
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public int insert(T record) {
        return getMapper().insert(record);
    }

    @Override
    public int insertSelective(T record) {
        int result = getMapper().insertSelective(record);
        return result;
    }

    //@Override
    //public int updateByPrimaryKey(T record) {return getMapper().updateByPrimaryKey(record);}

    @Override
    public int updateByPrimaryKeySelective(T record) {
        int result = getMapper().updateByPrimaryKeySelective(record);
        return result;
    }

    //@Override
    //public int updateByExample(T record, Object example) {return getMapper().updateByExample(record, example);}

    @Override
    public int updateByExampleSelective(T record, Object example) {
        int result = getMapper().updateByExampleSelective(record, example);
        return result;
    }

    @Override
    public int delete(T record) {
        return getMapper().delete(record);
    }

    @Override
    public int deleteByPrimaryKey(T record) {
        return getMapper().deleteByPrimaryKey(record);
    }

    @Override
    public List<T> select(T record) {
        return getMapper().select(record);
    }

    @Override
    public List<T> selectByExample(Object o) {
        return getMapper().selectByExample(o);
    }

    @Override
    public T selectByPrimaryKey(Object o) {
        return getMapper().selectByPrimaryKey(o);
    }

    @Override
    public T selectByPrimaryKeyAndDelFlag(Class<T> tClass, Object id, Object delFlag) {
        if(null == tClass || null == id || delFlag == null) {
            return null;
        }
        Example example = new Example(tClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        criteria.andEqualTo("delFlag", delFlag);
        return getMapper().selectOneByExample(example);
    }

    @Override
    public T selectOneByExample(Object o) {
        return getMapper().selectOneByExample(o);
    }

    @Override
    public int selectCountByExample(Object o) {
        return getMapper().selectCountByExample(o);
    }

    @Override
    public int selectCount(T record) {
        return getMapper().selectCount(record);
    }

    /**
     * 获取Mapper文件
     * @return mapper文件
     */
    abstract Mapper<T> getMapper();

}
