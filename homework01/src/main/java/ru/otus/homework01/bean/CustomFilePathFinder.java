package ru.otus.homework01.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Класс для предоставления пути к файлам вопросов с разными языками в зависимости от
 * текущей локали
 */
@Component
public class CustomFilePathFinder {
    @Value("${questions.file.ru}")
    private String pathToFileRu;// путь к файлу с вопросами на русском языке
    @Value("${questions.file.en}")
    private String pathToFileEn;// путь к файлу с вопросами на английском языке
    CustomLocaleEnvelope customLocaleEnvelope;
    public CustomFilePathFinder(CustomLocaleEnvelope customLocaleEnvelope) {
        this.customLocaleEnvelope = customLocaleEnvelope;
    }

    /**
     * В зависимости от локали вернет:
     * @return путь к файлу из application.yml
     */
    public String getPathToFile() {
        String language = customLocaleEnvelope.getDefaultLocale().getLanguage();
        if(language.equalsIgnoreCase("en")){
            return pathToFileEn;
        } else {
            return pathToFileRu;
        }
    }
}
