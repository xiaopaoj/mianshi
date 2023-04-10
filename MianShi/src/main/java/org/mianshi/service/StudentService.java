package org.mianshi.service;

import org.mianshi.model.StudentModel;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:53
 */
public interface StudentService extends BaseService<StudentModel> {

    StudentModel addStudent(StudentModel studentModel);

    StudentModel editStudent(StudentModel studentModel);

    void delStudent(Long id);

    void moveClass(Long id, Long classId);

    void exitClass(Long id);
}
