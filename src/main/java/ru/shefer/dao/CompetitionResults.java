package ru.shefer.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный класс хранит результаты соревнований после загрузки в формате списка
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
@Component
public class CompetitionResults {
    /**
     * Результаты соревнований после загрузки в формате списка
     */
    private final List<Result> results;


    /**
     * Создает новый объект с пустым списком результатов
     */
    public CompetitionResults() {
        this.results = new ArrayList<>();
    }

    /**
     * Возвращает список результатов сорвенований
     *
     * @return список результатов соревнований
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * Добаваляет результат одного спортсмена в список всех результатов
     *
     * @param result результат одного спортсмена, который необходимо добавить
     */
    public void addResult(Result result) {
        this.results.add(result);
    }
}
