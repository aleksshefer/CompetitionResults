package ru.shefer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shefer.dao.*;


import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultsProcessorImplTest {
    @Mock
    CompetitionResults competitionResults;
    @Mock
    ResultParserFromCsv resultParser;
    @Mock
    ResultLoaderFromFileSystem resultLoader;
    @InjectMocks
    ResultsProcessorImpl resultsProcessor;

    @Test
    void resultsIsLoaded() {
        resultsProcessor.resultsIsLoaded();
        verify(competitionResults, times(1)).getResults();
    }

    @Test
    void downloadAndParseResults() throws IOException, ParseException {
        when(resultLoader.downloadResults("src/main/resources/results.csv"))
                .thenReturn(new ArrayList<>(List.of("Name1, F, 1 км, 30:20")));
        resultsProcessor.downloadAndParseResults();
        verify(resultLoader, times(1)).downloadResults("src/main/resources/results.csv");

        verify(resultParser, times(1)).parseToResult("Name1, F, 1 км, 30:20", 1);
    }

    @Test
    void getAllResults() {
        resultsProcessor.resultsIsLoaded();
        verify(competitionResults, times(1)).getResults();
    }

    @Test
    void getResults() {
        Result result1 = new Result("Name1", Gender.M, 1, Duration.ofSeconds(30 * 60 + 20));
        Result result2 = new Result("Name2", Gender.M, 1, Duration.ofSeconds(20 * 60 + 20));
        Result result3 = new Result("Name3", Gender.F, 2, Duration.ofSeconds(30 * 60 + 20));
        Result result4 = new Result("Name4", Gender.F, 2, Duration.ofSeconds(10 * 60 + 20));
        Result result5 = new Result("Name5", Gender.M, 1, Duration.ofSeconds(10 * 60 + 20));
        when(competitionResults.getResults())
                .thenReturn(new ArrayList<>(List.of(result1, result2, result3, result4, result5)));
        List<Result> results = resultsProcessor.getResults(2, 1, Gender.M);
        verify(competitionResults, times(1)).getResults();
        assertEquals(2, results.size());
        assertEquals(result5, results.get(0));
        assertEquals(result2, results.get(1));

    }
}