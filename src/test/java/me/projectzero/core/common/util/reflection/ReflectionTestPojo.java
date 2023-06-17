package me.projectzero.core.common.util.reflection;

import lombok.Data;

import java.util.List;

@Data
public class ReflectionTestPojo {

    private String field1 = "Field 1";
    private Integer field2 = 2;
    private List<String> field3 = List.of("1", "2", "3");
    private String field4 = "Field 4";
    private String field5 = "Field 5";

}
