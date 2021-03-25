package ru.otus.homework01.bean;

import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Класс-обертка для локали
 */
@Component
public class CustomLocaleEnvelope {
    private  final Locale locale;

    public CustomLocaleEnvelope() {
        this.locale = Locale.getDefault();
        //this.locale = new Locale.Builder().setLanguage("en").setRegion("EN").build();
    }

    public Locale getDefaultLocale() {
        return locale;
    }

}
