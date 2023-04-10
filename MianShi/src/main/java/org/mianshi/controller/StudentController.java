package org.mianshi.controller;

import org.mianshi.common.ResponseResult;
import org.mianshi.common.exception.BaseException;
import org.mianshi.common.exception.ServerCode;
import org.mianshi.model.StudentModel;
import org.mianshi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:58
 */
@RestController()
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseResult add(@Valid @RequestBody StudentModel stu){
        studentService.addStudent(stu);
        return ResponseResult.success(stu.getId());
    }

    @PostMapping("/edit")
    public ResponseResult edit(@Valid @RequestBody StudentModel stu){
        if(null == stu.getId()) {
            throw new BaseException(ServerCode.WRONG_PARAM, "主键ID不能为空");
        }
        studentService.editStudent(stu);
        return ResponseResult.success(stu.getId());
    }

    @DeleteMapping("/del/{id}")
    public ResponseResult del(@NotNull(message = "主键ID不能为空") @PathVariable("id") Long id){
        studentService.delStudent(id);
        return ResponseResult.success(id);
    }

    @PutMapping("/moveClass/{id}/{classId}")
    public ResponseResult add(@NotNull(message = "主键ID不能为空") @PathVariable("id") Long id,
                              @NotNull(message = "班级ID不能为空") @PathVariable("classId") Long classId){
        studentService.moveClass(id, classId);
        return ResponseResult.success();
    }

    @PutMapping("/exitClass/{id}")
    public ResponseResult add(@NotNull(message = "主键ID不能为空") @PathVariable("id") Long id){
        studentService.exitClass(id);
        return ResponseResult.success();
    }

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.success(studentService.select(StudentModel.builder().build()));
    }
}
