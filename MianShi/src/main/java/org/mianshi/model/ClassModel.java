package org.mianshi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author : nate.jiang
 * @date : 2023/4/10 13:51
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class")
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 班级名称
     */
    @Column(name = "cls_name")
    @Size(min = 1,max = 10,message = "班级名称长度必须为1到10")
    private String clsName;
}
