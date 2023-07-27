package ru.shefer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.shefer.dao.Gender;
import ru.shefer.service.ResultsProcessor;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ResultsProcessor resultsProcessor = context.getBean(ResultsProcessor.class);

        System.out.println(resultsProcessor.getAllResults());
        System.out.println(resultsProcessor.resultsIsLoaded());
        resultsProcessor.downloadAndParseResults();
        System.out.println(resultsProcessor.resultsIsLoaded());
        System.out.println(resultsProcessor.getAllResults());
        System.out.println(resultsProcessor.getResults(10, 5, Gender.M));
    }
}