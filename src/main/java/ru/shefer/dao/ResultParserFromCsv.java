package ru.shefer.dao;

import org.springframework.stereotype.Component;
import ru.shefer.dao.interfaces.ResultParser;

import java.text.ParseException;
import java.time.Duration;

/**
 * Данный класс реализует интерфейс ResultParser с параметрос String. Преобразует строку в объект типа Result
 *
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
@Component
public class ResultParserFromCsv implements ResultParser<String> {
    /**
     * @param stringToParse преобразуемый элемент
     * @param lineNUmber    номер строки файла, из которой происходит преобразование
     * @return новый объект типа Result
     * @throws ParseException в случае ошибки преобразования строки в объект типа Result,
     *                        требуемый формат: Name, M, 1 км, 30:20
     */
    @Override
    public Result parseToResult(String stringToParse, int lineNUmber) throws ParseException {
        String[] stringsToParse = stringToParse.trim().split(", ");
        String name = stringsToParse[0];
        Gender gender = parseStringToGender(stringsToParse[1], lineNUmber);
        int distance = parseStringToDistance(stringsToParse[2], lineNUmber);
        Duration duration = parseStringToDuration(stringsToParse[3], lineNUmber);
        return new Result(name, gender, distance, duration);
    }

    /**
     * Преобразует строку в пол спортсмена типа Gender
     *
     * @param targetString преобразуемая строка
     * @param lineNumber   номер строки, в которой происходит преобразование
     * @return пол спортсмена типа Gender
     * @throws ParseException в случае ошибки преобразования строки,
     *                        поддерживаемый формат строки - "M" или "F" английского алфавита
     */
    private Gender parseStringToGender(String targetString, int lineNumber) throws ParseException {
        if (targetString.equals("M")) {
            return Gender.M;
        } else if (targetString.equals("F")) {
            return Gender.F;
        } else throw new ParseException("Can't parse gender ", lineNumber);
    }

    /**
     * Преобразует строку в значение int километров
     *
     * @param targetString преобразуемая строка
     * @param lineNUmber   номер строки, в которой происходит преобразование
     * @return дистаниция в километрах
     * @throws ParseException в случае ошибки пробразования строки,
     *                        поддерживаемый формат строки - "2 км"
     */
    private int parseStringToDistance(String targetString, int lineNUmber) throws ParseException {
        int resultOfParsing;
        String[] targetStrings = targetString.split(" ");
        try {
            resultOfParsing = Integer.parseInt(targetStrings[0]);
        } catch (NumberFormatException e) {
            throw new ParseException("Can't parse distance " + e.getMessage(), lineNUmber);
        }
        return resultOfParsing;
    }

    /**
     * Преобразует строку в объект типа Distance
     *
     * @param targetString преобразуемая строка
     * @param lineNumber   номер строки, в которой происходит преобразование
     * @return новый объект типа Duration
     * @throws ParseException в случае ошибки преобразования строки,
     *                        поддерживаемый формат строки - "1:24:11"
     */
    private Duration parseStringToDuration(String targetString, int lineNumber) throws ParseException {
        String[] strings = targetString.trim().split(":");
        try {
            if (strings.length == 1) {
                return Duration.ofSeconds(Integer.parseInt(strings[0]));
            } else if (strings.length == 2) {
                return Duration.ofSeconds(Integer.parseInt(strings[0]) * 60L + Integer.parseInt(strings[1]));
            } else if (strings.length == 3) {
                return Duration.ofSeconds(Integer.parseInt(strings[0]) * 3600L + Integer.parseInt(strings[1]) * 60L + Integer.parseInt(strings[2]));
            } else throw new ParseException("Can't parse duration", lineNumber);
        } catch (NumberFormatException e) {
            throw new ParseException("Can't parse duration " + e.getMessage(), lineNumber);
        }
    }
}
