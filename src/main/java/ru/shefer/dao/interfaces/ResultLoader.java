package ru.shefer.dao.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * Данный интерфейс предоставляет загрузчик результатов соревнований в CompetitionResults
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
public interface ResultLoader {

    /**
     * Загружает результаты соревноаний в CompetitionResults
     *
     * @param path путь обращения к списку
     * @return список строк с результатами
     * @throws IOException в случае ошибки чтения
     */
    List<String> downloadResults(String path) throws IOException;

}
