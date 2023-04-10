package org.mianshi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:43
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class StudentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 学号
     */
    @Column(name = "stu_no")
    @NotNull(message = "学号不能为空")
    private Integer stuNo;

    /**
     * 姓名
     */
    @Column(name = "stu_name")
    @Size(min = 1,max = 10,message = "姓名长度必须为1到10")
    private String stuName;

    /**
     * 性别
     */
    @Column(name = "stu_gender")
    @NotNull(message = "性别不能为空")
    @Pattern(regexp = "^[0,1]{1}$", message = "性别只能选择男女")
    private String stuGender;

    /**
     * 年龄
     */
    @Column(name = "stu_age")
    @Min(value = 1, message = "年龄必须大于1岁")
    private Integer stuAge;

    /**
     * 班级
     */
    @Column(name = "stu_class_id")
    private Long stuClassId;

    /**
     * 籍贯
     */
    @Column(name = "stu_native_place")
    private String stuNativePlace;
}
