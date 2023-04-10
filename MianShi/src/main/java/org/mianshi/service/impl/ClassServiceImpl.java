package org.mianshi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mianshi.common.exception.BaseException;
import org.mianshi.mapper.ClassMapper;
import org.mianshi.model.ClassModel;
import org.mianshi.model.StudentModel;
import org.mianshi.service.ClassService;
import org.mianshi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:54
 */
@Slf4j
@Service
public class ClassServiceImpl extends BaseServiceImpl<ClassModel> implements ClassService {

    @Autowired
    private ClassMapper mapper;

    @Autowired
    private StudentService studentService;

    @Override
    Mapper<ClassModel> getMapper() {
        return mapper;
    }

    @Override
    public ClassModel addClass(ClassModel cls) {
        if(checkClass(cls.getId(), cls.getClsName())) {
            throw new BaseException("班级名称已存在");
        }
        mapper.insertSelective(cls);
        return cls;
    }

    @Override
    public ClassModel editClass(ClassModel cls) {
        if(checkClass(cls.getId(), cls.getClsName())) {
            throw new BaseException("班级名称已存在");
        }
        ClassModel oldModel =  mapper.selectByPrimaryKey(cls.getId());
        if(null == oldModel) {
            throw new BaseException("班级信息不存在");
        }
        log.info("数据修改前 {} \n 数据修改后 {}", oldModel.toString(), cls);
        mapper.updateByPrimaryKeySelective(cls);
        return cls;
    }

    private boolean checkClass(Long id, String clsName) {
        Example example = new Example(ClassModel.class);
        example.createCriteria()
                .andEqualTo("clsName", clsName)
                .andNotEqualTo("id", id);
        int cn = mapper.selectCountByExample(example);
        return cn > 0;
    }

    @Override
    public void delClass(Long id) {
        ClassModel oldModel =  mapper.selectByPrimaryKey(id);
        if(null == oldModel) {
            throw new BaseException("班级信息不存在");
        }
        int cn = studentService.selectCount(StudentModel.builder().stuClassId(id).build());
        if(cn > 0) {
            throw new BaseException("班级内存在关联学生无法删除");
        }
        log.info("数据被删除 {}", oldModel.toString());
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveClass(Long id, Long newClassId) {
        ClassModel newModel =  mapper.selectByPrimaryKey(newClassId);
        if(null == newModel) {
            throw new BaseException("新班级信息不存在");
        }
        List<StudentModel> stuList = studentService.select(StudentModel.builder().stuClassId(id).build());
        if(stuList.size() <= 0) {
            throw new BaseException("班级内不存在需要操作的学生数据");
        }
        for (StudentModel studentModel : stuList) {
            studentService.moveClass(studentModel.getId(), newClassId);
        }
        log.info("移动 {} 个学生到新班级 {}", stuList.size(), newModel.toString());
    }
}
