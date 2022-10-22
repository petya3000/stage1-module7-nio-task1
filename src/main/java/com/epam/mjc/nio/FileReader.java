package com.epam.mjc.nio;

import java.io.*;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String name;
        int age;
        String email;
        long phone;
        try (BufferedReader inputStream = new BufferedReader(new java.io.FileReader(file))) {
            name = inputStream.readLine().substring(6);
            age = Integer.parseInt(inputStream.readLine().substring(5));
            email = inputStream.readLine().substring(7);
            phone = Long.parseLong(inputStream.readLine().substring(7));
            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
