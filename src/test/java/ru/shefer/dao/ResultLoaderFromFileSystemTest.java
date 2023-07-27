package ru.shefer.dao;

import org.junit.jupiter.api.Test;
import ru.shefer.dao.interfaces.ResultLoader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultLoaderFromFileSystemTest {

    ResultLoader resultLoader = new ResultLoaderFromFileSystem();


    @Test
    void downloadResults() throws IOException {
        List<String> stringsResults = resultLoader.downloadResults("src/test/resources/testResults.csv");
        assertEquals(5, stringsResults.size());
        assertEquals("Name1, F, 1 км, 30:20", stringsResults.get(0));
        assertEquals("Name2, F, 1 км, 29:20", stringsResults.get(1));
        assertEquals("Name3, M, 2 км, 28:20", stringsResults.get(2));
        assertEquals("Name4, M, 2 км, 27:20", stringsResults.get(3));
        assertEquals("Name5, M, 10 км, 26:20", stringsResults.get(4));

    }
}