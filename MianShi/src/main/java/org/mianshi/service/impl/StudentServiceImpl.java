package org.mianshi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mianshi.common.exception.BaseException;
import org.mianshi.mapper.StudentMapper;
import org.mianshi.model.ClassModel;
import org.mianshi.model.StudentModel;
import org.mianshi.service.ClassService;
import org.mianshi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:54
 */
@Slf4j
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentModel> implements StudentService {

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private ClassService classService;

    @Override
    Mapper<StudentModel> getMapper() {
        return mapper;
    }

    @Override
    public StudentModel addStudent(StudentModel studentModel) {
        checkStu(studentModel);
        mapper.insertSelective(studentModel);
        return studentModel;
    }

    @Override
    public StudentModel editStudent(StudentModel studentModel) {
        checkStu(studentModel);
        StudentModel oldModel =  mapper.selectByPrimaryKey(studentModel.getId());
        if(null == oldModel) {
            throw new BaseException("学生信息不存在");
        }
        log.info("数据修改前 {} \n 数据修改后 {}", oldModel.toString(), studentModel);
        mapper.updateByPrimaryKeySelective(studentModel);
        return studentModel;
    }

    private void checkStu(StudentModel studentModel) {
        Example example = new Example(StudentModel.class);
        example.createCriteria()
                        .andEqualTo("stuNo", studentModel.getStuNo())
                                .andNotEqualTo("id", studentModel.getId());
        int cn = mapper.selectCountByExample(example);
        if(cn > 0) {
            throw new BaseException("学号已存在");
        }
        if(null != studentModel.getStuClassId()) {
            ClassModel newClass = classService.selectByPrimaryKey(studentModel.getStuClassId());
            if(null == newClass) {
                throw new BaseException("班级信息不存在");
            }
        }
    }

    @Override
    public void delStudent(Long id) {
        StudentModel oldModel =  mapper.selectByPrimaryKey(id);
        if(null == oldModel) {
            throw new BaseException("学生信息不存在");
        }
        log.info("数据被删除 {}", oldModel.toString());
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void moveClass(Long id, Long classId) {
        StudentModel oldModel =  mapper.selectByPrimaryKey(id);
        if(null == oldModel) {
            throw new BaseException("学生信息不存在");
        }
        ClassModel newClass = classService.selectByPrimaryKey(classId);
        if(null == newClass) {
            throw new BaseException("新班级不存在");
        }
        log.info("数据移动前 {} 移动到新班级 {}", oldModel.toString(), newClass.toString());
        mapper.updateByPrimaryKeySelective(StudentModel.builder()
                .id(id).stuClassId(classId).build());
    }

    @Override
    public void exitClass(Long id) {
        StudentModel oldModel =  mapper.selectByPrimaryKey(id);
        if(null == oldModel) {
            throw new BaseException("学生信息不存在");
        }
        log.info("学生被移除当前班级 {} ", oldModel.toString());
        oldModel.setStuClassId(null);
        mapper.updateByPrimaryKey(oldModel);
    }
}
