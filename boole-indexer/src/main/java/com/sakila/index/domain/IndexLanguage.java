package com.sakila.index.domain;

import com.sakila.domain.Language;
import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexLanguage extends AbstractIndexEntity {

    private String name;

    public IndexLanguage() {
    }

    public IndexLanguage(Language language1) {
        if (language1 != null) {
            setName(language1.getName());
            setId(language1.getLanguageId());
            setCreated((language1.getLastUpdated() != null)
                    ? new DateTime(language1.getLastUpdated().getTime())
                    : new DateTime());
            setUpdated((language1.getLastUpdated() != null)
                    ? new DateTime(language1.getLastUpdated().getTime())
                    : new DateTime());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexLanguage that = (IndexLanguage) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
