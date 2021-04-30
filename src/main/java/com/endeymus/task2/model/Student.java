package com.endeymus.task2.model;


import java.sql.Date;

/**
 * @author Mark Shamray
 */
public class Student {
    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthDate;
    private String group;
    private String studentId;

    @Override
    public String toString() {
        return  "id: " + id +
                ", Имя: " + firstName  +
                ", Фамилия: " + secondName  +
                ", Отчество: " + middleName +
                ", Дата рождения:  " + birthDate +
                ", Номер группы: " + group +
                ", Уникальный идентификатор: " + studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
