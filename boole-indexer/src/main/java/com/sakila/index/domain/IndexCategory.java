package com.boole.index.domain;

import com.boole.domain.Category;
import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexCategory extends AbstractIndexEntity {
    private String name;

    public IndexCategory() {
    }

    public IndexCategory(Category category) {
        if (category != null) {
            setId(category.getCategoryId());
            setName(category.getName());
            setCreated((category.getLastUpdated() != null)
                    ? new DateTime(category.getLastUpdated().getTime())
                    : new DateTime());
            setUpdated((category.getLastUpdated() != null)
                    ? new DateTime(category.getLastUpdated().getTime())
                    : new DateTime());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexCategory that = (IndexCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
