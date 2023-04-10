package org.mianshi.controller;

import org.mianshi.common.ResponseResult;
import org.mianshi.common.exception.BaseException;
import org.mianshi.common.exception.ServerCode;
import org.mianshi.model.ClassModel;
import org.mianshi.model.StudentModel;
import org.mianshi.service.ClassService;
import org.mianshi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 14:53
 */
@RestController()
@RequestMapping(value = "/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/add")
    public ResponseResult add(@Valid @RequestBody ClassModel cls){
        classService.addClass(cls);
        return ResponseResult.success(cls.getId());
    }

    @PostMapping("/edit")
    public ResponseResult edit(@Valid @RequestBody ClassModel cls){
        if(null == cls.getId()) {
            throw new BaseException(ServerCode.WRONG_PARAM, "主键ID不能为空");
        }
        classService.editClass(cls);
        return ResponseResult.success(cls.getId());
    }

    @DeleteMapping("/del/{id}")
    public ResponseResult del(@NotNull(message = "主键ID不能为空") @PathVariable("id") Long id){
        classService.delClass(id);
        return ResponseResult.success(id);
    }

    @PutMapping("/moveClass/{id}/{newClassId}")
    public ResponseResult moveClass(@NotNull(message = "主键ID不能为空") @PathVariable("id") Long id,
                              @NotNull(message = "新班级ID不能为空") @PathVariable("newClassId") Long newClassId){
        classService.moveClass(id, newClassId);
        return ResponseResult.success(id);
    }

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.success(classService.select(ClassModel.builder().build()));
    }
}
