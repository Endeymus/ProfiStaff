package com.endeymus.task2.rep;

import com.endeymus.task2.config.ConnectionDB;
import com.endeymus.task2.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, предоставлющий методы взаимодействия с БД
 * @author Mark Shamray
 */

public class StudentDaoImpl implements StudentDao {

    private static final String SQL_FIND_ALL = "select * from students";
    private static final String SQL_FIND_BY_STUDENT_ID = "select * from Students s where s.student_id = ?";
    private static final String SQL_INSERT = "insert into students(first_name, second_name, middle_name, birthdate, students.group, student_id) " +
            "values (?, ?, ?, ?, ?, ?)";
    //               1, 2, 3, 4, 5, 6
    private static final String SQL_DELETE = "delete from students where student_id = ?";

    /**
     * Добавление нового студента
     * @param contract новый студент
     * @return с случае успешного выполнения возвращает добавленого студента
     */
    @Override
    public Student insert(Student contract) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {

            preparedStatement.setString(1, contract.getFirstName());
            preparedStatement.setString(2, contract.getSecondName());
            preparedStatement.setString(3, contract.getMiddleName());
            preparedStatement.setDate(4, contract.getBirthDate());
            preparedStatement.setString(5, contract.getGroup());
            preparedStatement.setString(6, contract.getStudentId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contract;
    }

    /**
     * Получение списка студентов
     * @return возвращает список {@link List} студентов
     */
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionDB.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                students.add(getStudent(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    /**
     * Вспомогательный метод, обрабатывающий данные из результирующего набора данных
     * @param resultSet набор данных
     * @return студента {@link Student}
     * @throws SQLException если какое-либо поле некоректно; доступ к БД завершился с ошибкой; набор данных закрыт
     */
    private Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setSecondName(resultSet.getString("second_name"));
        student.setMiddleName(resultSet.getString("middle_name"));
        student.setBirthDate(resultSet.getDate("birthdate"));
        student.setGroup(resultSet.getString("group"));
        student.setStudentId(resultSet.getString("student_id"));
        return student;
    }

    /**
     * Поиск студента по уникальному идентификатору
     * @param studentId уникальный идентификатор
     * @return студента {@link Student}
     */
    @Override
    public Student findByStudentId(String studentId) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_STUDENT_ID)) {

            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getStudent(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Удаление существующего студента
     * @param contract студент
     */
    @Override
    public void delete(Student contract) {
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {

            preparedStatement.setString(1, contract.getStudentId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
