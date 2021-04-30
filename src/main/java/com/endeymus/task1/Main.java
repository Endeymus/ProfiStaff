package com.endeymus.task1;

import java.io.IOException;

/**
 * Класс демонстрирубщий работу основного класса
 * @author Mark Shamray
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\endey\\IdeaProjects\\ProfiStaff\\src\\main\\resources\\testDir";
        FileVisitor.get(path);
    }
}
