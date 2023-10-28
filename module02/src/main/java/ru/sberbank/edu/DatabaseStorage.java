package ru.sberbank.edu;

public class DatabaseStorage implements Storage{

    /***
     * Сохраняет переданные аргументы в базу данных
     * @param lineCount
     * @param spaceCount
     * @param line
     */
    @Override
    public void save(int lineCount, int spaceCount, String line) {
        System.out.println("Preparation before saving data to the database...");
        System.out.println("The data is saved to the database." + "\n"
                + "lineCount= " + lineCount + "\n"
                + "spaceCount= " + spaceCount + "\n"
                + "line= " + line);
    }
}

