package com.stewart.building.to;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Stewart
 * @create 2022/5/18
 * @Description
 */
@Accessors(chain = true)
@Data
public class TeacherTo implements Serializable {
    private String name;

    private String email;
}
