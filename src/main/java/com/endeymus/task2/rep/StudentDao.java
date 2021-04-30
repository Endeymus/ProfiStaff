package com.endeymus.task2.rep;

import com.endeymus.task2.model.Student;

import java.util.List;

/**
 * @author Mark Shamray
 */
public interface StudentDao {
    Student insert(Student contract);
    List<Student> findAll();
    Student findByStudentId(String studentId);
    void delete(Student contract);
}
