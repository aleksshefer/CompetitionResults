package ru.shefer.dao;

import org.junit.jupiter.api.Test;
import ru.shefer.dao.interfaces.ResultParser;

import java.text.ParseException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ResultParserFromCsvTest {
    ResultParser<String> resultParser = new ResultParserFromCsv();

    @Test
    void parseToResult() throws ParseException {
        Result result = new Result("Name1", Gender.M, 1, Duration.ofSeconds(30 * 60 + 20));

        assertEquals(result, resultParser.parseToResult("Name1, M, 1 км, 30:20", 1));
        assertThrows(ParseException.class, () -> resultParser.parseToResult("Name1, D, 1 км, 30:20", 1));
        assertThrows(ParseException.class, () -> resultParser.parseToResult("Name1, M, one км, 30:20", 1));
        assertThrows(ParseException.class, () -> resultParser.parseToResult("Name1, M, 1 км, 30:twenty", 1));
        try {
            resultParser.parseToResult("Name1, D, 1 км, 30:20", 10);
        } catch (ParseException e) {
            assertEquals(10, e.getErrorOffset());
        }
    }
}