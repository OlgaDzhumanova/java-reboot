package ru.sberbank.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStatistics implements Statistic {
    private int lineCount;
    private int spaceCount;
    private String longestLine;
    private File file;
    private Storage storage;
    private TypesDataSave typesDataSave;
    private List<String> lines = new ArrayList<>();


    public FileStatistics(File file, TypesDataSave typesDataSave) {
        this.typesDataSave = typesDataSave;
        this.file = file;
        readFile(file);
    }

    /***
     * Считывает информаци из переданного файла и сохраняет их
     * в коллекцию типа ArrayList
     * Если передан не существующий файл выкидывает исключение
     * @param file
     * @return
     */
    private List<String> readFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            lines = reader.lines().toList();
            reader.close();
        } catch (IOException e) {
            System.out.println("File reading error. Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return lines;
    }

    /***
     * Возвращает число типа int равное количеству строк в файле
     * @return
     */
    @Override
    public int getLineCount() {
        return lineCount = lines.size();
    }

    /***
     * Возвращает число типа int равное количеству пробелов в строках в файле
     * @return
     */
    @Override
    public int getSpaceCount() {
        for (String str : lines) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    spaceCount += 1;
                }
            }
        }
        return spaceCount;
    }

    /***
     * Возвращает самую длинную строку в файле типа String
     * @return
     */
    @Override
    public String getLongestLine() {
        if (lines.isEmpty()) {
            return "File is empty";
        }
        longestLine = lines.get(0);
        for (String line : lines) {
            if (line.length() > longestLine.length()) {
                longestLine = line;
            }
        }
        return longestLine;
    }

    /***
     * Сохраняет собранные данные в файл или базу данных
     * при выбранном типе сохранения typesDataSave
     * @param lineCount
     * @param spaceCount
     * @param line
     */
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        switch (typesDataSave) {
            case BD -> {
                storage = new DatabaseStorage();
                storage.save(lineCount, spaceCount, line);
            }
            case FL -> {
                storage = new FileStorage();
                storage.save(lineCount, spaceCount, line);
            }
        }
    }
}