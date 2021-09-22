package com.stu.luanvan.exception;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class MyMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator defaultInterpolator;

    public MyMessageInterpolator(MessageInterpolator defaultInterpolator) {
        this.defaultInterpolator = defaultInterpolator;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        messageTemplate = messageTemplate.toUpperCase();
        return defaultInterpolator.interpolate(messageTemplate, context);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        messageTemplate = messageTemplate.toUpperCase();
        return defaultInterpolator.interpolate(messageTemplate, context, locale);
    }
}
