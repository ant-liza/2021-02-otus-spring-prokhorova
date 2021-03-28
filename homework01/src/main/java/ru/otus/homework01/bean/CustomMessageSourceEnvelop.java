package ru.otus.homework01.bean;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CustomMessageSourceEnvelop {
    private final MessageSource messageSource;
    private final CustomLocaleEnvelope localeEnvelope;

    public CustomMessageSourceEnvelop(MessageSource messageSource,
                                      CustomLocaleEnvelope localeEnvelope) {
        this.messageSource = messageSource;
        this.localeEnvelope = localeEnvelope;
    }

    public String getMessage(String value) {
        return messageSource.getMessage(value,
                null, localeEnvelope.getDefaultLocale());
    }
}
