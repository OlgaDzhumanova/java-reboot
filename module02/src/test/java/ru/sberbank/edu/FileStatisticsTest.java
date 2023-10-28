package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileStatisticsTest {

    @Test
    void returnsNumberOfLines() {
        FileStatistics fileStatistics = new FileStatistics(new File("inputFile.txt"),TypesDataSave.BD);
        int count = fileStatistics.getLineCount();
        Assert.assertEquals(5, count);
    }

    @Test
    void returnsSpaceCount() {
        FileStatistics fileStatistics = new FileStatistics(new File("inputFile.txt"),TypesDataSave.BD);
        int count = fileStatistics.getSpaceCount();
        Assert.assertEquals(7, count);
    }

    @Test
    void returnsLongestLine() {
        FileStatistics fileStatistics = new FileStatistics(new File("inputFile.txt"),TypesDataSave.BD);
        String srt = fileStatistics.getLongestLine();
        Assert.assertTrue(srt.equals("Мои кошки-хулиганки!!!!!"));
    }
}