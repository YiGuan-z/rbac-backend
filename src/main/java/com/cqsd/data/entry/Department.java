package com.cqsd.data.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    /** */
    private Long id;

    /** */
    private String name;

    /** */
    private String sn;
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("sn='" + sn + "'")
                .toString();
    }
}