package com.endeymus.task2.util;

import com.endeymus.task2.model.Student;

import java.sql.Date;

/**
 * Вспомогательный класс, упрощающий провутку и обработку входных данных
 * @author Mark Shamray
 */
public class StudentParser {
    /**
     * Единственный метод, проверяющий корректность входных данных
     * Приводит введенную дату рождения к нужному формату
     * @param firstName имя студента
     * @param secondName фамилия
     * @param middleName отчетсво
     * @param birthDate дата рождения
     * @param group учебная группа
     * @param stID уникальный идентификатор
     * @return студента {@link Student}
     */
    public static Student parse(String firstName, String secondName, String middleName, String birthDate, String group, String stID) {
        if (firstName.equals("") || secondName.equals("") || group.equals("") || stID.equals("") )
            throw new NullPointerException();
        String pBirthDate = "";
        if (birthDate.contains(".")) {
            pBirthDate = birthDate.replaceAll("\\.", "-");
        }
        Student student = new Student();
        student.setFirstName(firstName);
        student.setSecondName(secondName);
        student.setMiddleName(middleName);
        student.setBirthDate(Date.valueOf(pBirthDate));
        student.setGroup(group);
        student.setStudentId(stID);

        return student;
    }
}
