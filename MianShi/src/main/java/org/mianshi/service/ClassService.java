package org.mianshi.service;

import org.mianshi.model.ClassModel;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:53
 */
public interface ClassService extends BaseService<ClassModel> {

    ClassModel addClass(ClassModel cls);

    ClassModel editClass(ClassModel cls);

    void delClass(Long id);

    void moveClass(Long id, Long newClassId);
}
