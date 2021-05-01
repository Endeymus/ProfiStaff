package com.endeymus.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Класс осуществляющий поиск всех текстовых файлов, сортирующий их и объединяющий содержимое в один файл
 * @author Mark Shamray
 */
public class FileVisitor {
    private static Path path;
    private static List<Path> files;
    private static Path dest;

    /**
     * Рекурсивный обход корневой папки и добавление всех файлов в список
     * @throws IOException ошибка ввода/вывода
     */
    private static void visit() throws IOException {
        Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(file->file.getFileName().toString().toLowerCase().endsWith(".txt"))
                .sorted(Comparator.comparing(Path::getFileName))
                .forEach(files::add);
    }

    /**
     * Запись содержимого всех файлов в один
     * @throws IOException ошибка ввода/вывода
     */
    private static void getContents() throws IOException {
        dest = Files.createFile(path
                .resolve(new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-s").format(new Date().getTime()) + ".txt")
        );

        files.forEach(x->{
            try {
                getContent(x, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Содержимое всех текстовых файлов успешно объединено, полученный файл: " + dest);
    }

    /**
     * Запись содержимого входного файла в выходной
     * @param source входной файл
     * @param dest выходной файл
     * @throws IOException ошибка ввода/вывода
     */
    private static void getContent(Path source, Path dest) throws IOException {
        List<String> lines = Files.readAllLines(source);
        Files.write(dest, lines, StandardOpenOption.APPEND);
    }

    /**
     * Поиск всех текстовых файлов, сортировать их и объединить содержимое в один файл
     * @param source корневой каталог
     * @throws IOException ошибка ввода/вывода
     */
    public static void get(String source) throws IOException {
        path = Paths.get(source);
        files = new ArrayList<>();
        visit();
        getContents();
    }
}
