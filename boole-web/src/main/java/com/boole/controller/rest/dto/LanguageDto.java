package com.boole.controller.rest.dto;

/**
 * Created on 10/22/2015.
 */
public class LanguageDto extends AbstractDto {
    private String name;
    private boolean original = false;

    public LanguageDto() {
    }

    public LanguageDto(LanguageDtoBuilder languageDtoBuilder) {
        this(languageDtoBuilder.name, languageDtoBuilder.original);
    }

    public LanguageDto(String name, boolean original) {
        this.name = name;
        this.original = original;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public static class LanguageDtoBuilder {
        private String name;
        private boolean original;

        public LanguageDtoBuilder original(boolean original) {
            this.original = original;
            return this;
        }

        public LanguageDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LanguageDto build() {
            return new LanguageDto(this);
        }
    }
}
