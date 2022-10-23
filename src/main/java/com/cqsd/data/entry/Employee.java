package com.cqsd.data.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.StringJoiner;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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