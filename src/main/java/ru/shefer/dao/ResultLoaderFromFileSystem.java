package ru.shefer.dao;

import org.springframework.stereotype.Service;
import ru.shefer.dao.interfaces.ResultLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс реализует интерйфейс ResultLoader с загрузкой результатов соревнований из директории
 * по пути path
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
@Service
public class ResultLoaderFromFileSystem implements ResultLoader {
    /**
     * Загружает результаты сорвенований из файла, преобразует их в список при помощи resultParser
     *
     * @throws IOException в случае ошибки чтения файла
     *
     * @return список строк
     */
    @Override
    public List<String> downloadResults(String path) throws IOException {
        List<String> results = new ArrayList<>();
        String loadedString;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((loadedString = br.readLine()) != null) {
                results.add(loadedString);
            }
        }
        return results;
    }
}
