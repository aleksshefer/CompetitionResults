package ru.shefer.service;

import org.springframework.stereotype.Service;
import ru.shefer.dao.CompetitionResults;
import ru.shefer.dao.Gender;
import ru.shefer.dao.Result;
import ru.shefer.dao.interfaces.ResultLoader;
import ru.shefer.dao.interfaces.ResultParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Данный класс реализует интерфейс ResultsProcessor, представляет имплементацию обработчика
 * результатов соревнований
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
@Service
public class ResultsProcessorImpl implements ResultsProcessor {
    /**
     * Результаты соревнований
     */
    private final CompetitionResults competitionResults;
    private final ResultParser<String> resultParser;
    /**
     * Загрузчик результатов соревнований
     */
    private final ResultLoader resultLoader;

    /**
     * Создает новый обработчик результатов соревнований
     *
     * @param competitionResults Результаты соревнований
     * @param resultParser       Преобразователь строки в объект типа Result
     * @param resultLoader       Загрузчик результатов соревнований
     */

    public ResultsProcessorImpl(CompetitionResults competitionResults, ResultParser<String> resultParser, ResultLoader resultLoader) {
        this.competitionResults = competitionResults;
        this.resultParser = resultParser;
        this.resultLoader = resultLoader;
    }

    /**
     * Проверяет заполенность списка результатов соревнований
     *
     * @return true если список результатов соревнований не пуст,
     * false если список пуст
     */
    @Override
    public boolean resultsIsLoaded() {
        return !competitionResults.getResults().isEmpty();
    }

    /**
     * Инициирует загрузку результатов соревнований у ResultLoader из папки "src/main/resources/results.csv"
     * и преобразование в CompetitionResults
     */
    @Override
    public void downloadAndParseResults() {
        try {
            List<String> stringsResults = this.resultLoader.downloadResults("src/main/resources/results.csv");
            for (int i = 0; i < stringsResults.size(); i++) {
                competitionResults.addResult(resultParser.parseToResult(stringsResults.get(i), i + 1));
            }
        } catch (IOException e) {
            System.out.println("Unable to download results");
        } catch (ParseException e) {
            System.out.println("Unable to parse results in line: " + e.getErrorOffset() + " " + e.getMessage());
        }
    }

    /**
     * Возвращает список результатов соревноаний
     *
     * @return список результатов соревнований в виде объектов типа Result
     */
    @Override
    public List<Result> getAllResults() {
        if (competitionResults.getResults().isEmpty()) {
            System.out.println("results not loaded");
        }
        return competitionResults.getResults();
    }

    /**
     * Возвращает отформатированный список результатов соревнований, согласно переданным параметрам.
     * Список отсортирован по времени результата
     *
     * @param amountOfResults количесвто требуемых результатов в формате int
     * @param distance        требуемая дистанция в формате int
     * @param gender          тербуемый пол спортсмена в формате Gender
     * @return Отформатированный и отсортированный список результатов соревнований
     */
    @Override
    public List<Result> getResults(int amountOfResults, int distance, Gender gender) {
        return competitionResults.getResults().stream().filter((result) -> result.distance() == distance).filter((result) -> result.gender() == gender).sorted(Comparator.comparing(Result::duration)).limit(amountOfResults).collect(Collectors.toList());
    }
}
