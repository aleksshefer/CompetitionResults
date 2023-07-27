package ru.shefer.service;

import ru.shefer.dao.Gender;
import ru.shefer.dao.Result;

import java.util.List;

/**
 * Данный интерфейс предоставляет обработчик результатов соревнований
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
public interface ResultsProcessor {
    /**
     * Проверяет заполненность списка результатов соревнований
     *
     * @return true если список результатов соревнований не пуст,
     * false если список пуст
     */
    boolean resultsIsLoaded();

    /**
     * Инициирует загрузку результатов соревнований у ResultLoader
     */
    void downloadAndParseResults();

    /**
     * Возвращает список результатов соревноаний
     *
     * @return список результатов соревнований в виде объектов типа Result
     */
    List<Result> getAllResults();

    /**
     * Возвращает отформатированный список результатов соревнований, согласно переданным параметрам.
     * Список отсортирован по времени результата
     *
     * @param amountOfResults количесвто требуемых результатов в формате int
     * @param distance        требуемая дистанция в формате int
     * @param gender          тербуемый пол спортсмена в формате Gender
     * @return Отформатированный и отсортированный список результатов соревнований
     */
    List<Result> getResults(int amountOfResults, int distance, Gender gender);
}
