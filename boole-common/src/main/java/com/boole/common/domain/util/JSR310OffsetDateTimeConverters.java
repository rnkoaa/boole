package com.boole.common.domain.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
public class JSR310OffsetDateTimeConverters {
    private JSR310OffsetDateTimeConverters() {
    }
/*

    public static class LocalDateToDateConverter implements Converter<LocalDate, Date> {

        public static final LocalDateToDateConverter INSTANCE = new LocalDateToDateConverter();

        private LocalDateToDateConverter() {}

        @Override
        public Date convert(LocalDate source) {
            return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    public static class DateToLocalDateConverter implements Converter<Date, LocalDate> {
        public static final DateToLocalDateConverter INSTANCE = new DateToLocalDateConverter();
        private DateToLocalDateConverter() {}

        @Override
        public LocalDate convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
    }*/
}
