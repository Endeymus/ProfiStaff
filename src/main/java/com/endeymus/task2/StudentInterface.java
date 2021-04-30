package com.endeymus.task2;

import com.endeymus.task2.model.Student;
import com.endeymus.task2.rep.StudentDao;
import com.endeymus.task2.rep.StudentDaoImpl;
import com.endeymus.task2.util.StudentParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

/**
 * Консольный пользовательский интерфейс, с помощью которого можно взаимодействовать со студентами
 * @author Mark Shamray
 */
public class StudentInterface {

    private static StudentDao studentDao = new StudentDaoImpl();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static boolean exit = false;

    public static void main(String[] args) throws IOException {
        start();
    }

    /**
     * Запуск приложения
     * @throws IOException возможная ошибка во время ввода/ввывода
     */
    public static void start() throws IOException {
        System.out.println("Добро пожаловать в систему управления студентами!!!");
        while (!exit) {
            showCommand();

            String select = reader.readLine();

            switch (select) {
                case "1" -> addStudent();
                case "2" -> deleteStudent();
                case "3" -> {
                    System.out.print("\nВведите уникальный идентификатор студента: ");
                    String stID = reader.readLine();
                    if (stID.equals("")) {
                        System.out.println("Некорректный ввод!");
                        break;
                    }
                    System.out.println(studentDao.findByStudentId(stID));
                }
                case "4" -> {
                    System.out.println("\nСписок студентов:");
                    studentDao.findAll().forEach(System.out::println);
                }
                case "5" -> {
                    System.out.println("\nПока-пока!");
                    exit = true;
                }

                default -> System.out.println("\nПовторите ввод!!!");
            }
        }
        reader.close();
    }

    /**
     * Отображения допустимых команд
     */
    private static void showCommand() {
        System.out.println("1. Добавить студента");
        System.out.println("2. Удалить студента");
        System.out.println("3. Вывести студента по укникальному идентификатору");
        System.out.println("4. Вывести список всех студентов");
        System.out.println("5. Завершить работу");
        System.out.print("Введите комманду: ");
    }

    /**
     * Метод добавления удаления
     * @throws IOException возможная ошибка во время ввода/ввывода
     */
    private static void addStudent() throws IOException {
        System.out.print("\nВведите имя студента: ");
        String firstName = reader.readLine();
        System.out.print("Введите фамилию студента: ");
        String secondName = reader.readLine();
        System.out.print("Введите отчество студента: ");
        String middleName = reader.readLine();
        System.out.print("Введите дату рождения студента в формате yyyy-[m]m-[d]d: ");
        String birthDate = reader.readLine();
        System.out.print("Введите учебную группы студента: ");
        String group = reader.readLine();
        System.out.print("Введите уникальный идентификатор студента: ");
        String stID = reader.readLine();

        Student student;
        try {
            student = StudentParser.parse(firstName, secondName, middleName, birthDate, group, stID);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверно введена дата, повторите попытку.");
            return;
        } catch (Exception e) {
            System.out.println("Неверно заполнена форма, повторите попытку");
            return;
        }

        studentDao.insert(student);

    }

    /**
     * Метод удаления студента
     * @throws IOException возможная ошибка во время ввода/ввывода
     */
    private static void deleteStudent() throws IOException {
        System.out.print("\nВведите уникальный идентификатор студента: ");
        String stID = reader.readLine();
        if (stID.equals("")) {
            System.out.println("Некорректный ввод!");
            return;
        }
        Student find = studentDao.findByStudentId(stID);
        studentDao.delete(find);
        System.out.println("Студент удален!!!");
    }





}
