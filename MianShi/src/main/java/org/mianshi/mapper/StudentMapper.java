package org.mianshi.mapper;

import org.apache.ibatis.annotations.Param;
import org.mianshi.model.StudentModel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:50
 */
@Repository
public interface StudentMapper extends Mapper<StudentModel> {

}
