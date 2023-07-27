package ru.shefer.dao.interfaces;

import ru.shefer.dao.Result;

import java.text.ParseException;

/**
 * Данный интерфейс предоставляет функцию преобразования элемента типа T в объект типа Result
 *
 * @param <T> тип исходного элемента для парсинга
 * @author aleksandr shefer
 * @version 1
 * @since 1
 */
public interface ResultParser<T> {
    /**
     * Преобразует элемент Т в объект типа Result
     *
     * @param t          преобразуемый элемент
     * @param lineNumber номер строки файла, из которой происходит преобразование
     * @return новый объект типа Result
     * @throws ParseException в случае неверного формата строки
     */
    Result parseToResult(T t, int lineNumber) throws ParseException;

}
