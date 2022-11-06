package com.cqsd.data.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.StringJoiner;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    /** */
    private Long id;

    /** */
    private String username;

    /** */
    private String name;

    /** */
    @JsonIgnore
    private String password;

    /** */
    private String email;

    /** */
    private Integer age;

    /** */
    private Boolean admin;

    /** */
    private Long dept_id;

    /** */
    private Date hireDate;
    
    public Boolean getAdmin() {
        return admin != null;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("name='" + name + "'")
                .add("password='" + password + "'")
                .add("email='" + email + "'")
                .add("age=" + age)
                .add("admin=" + admin)
                .add("dept_id=" + dept_id)
                .add("hireDate=" + hireDate)
                .toString();
    }
}