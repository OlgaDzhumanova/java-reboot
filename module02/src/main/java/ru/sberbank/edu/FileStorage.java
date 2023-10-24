package ru.sberbank.edu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage implements Storage{
    /***
     * Сохраняет переданные аргументы в файл
     * @param lineCount
     * @param spaceCount
     * @param line
     */
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        System.out.println("Preparing before saving the data to a file...");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"))) {
            writer.write(String.valueOf(lineCount));
            writer.newLine();
            writer.write(String.valueOf(spaceCount));
            writer.newLine();
            writer.write(line);
        } catch (IOException e) {
            System.out.println("Error saving to file. Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}