package com.my.db;

public class Keyword {
  private Long id;
  private String keyword;
  private String phonetic_code;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getPhonetic_code() {
    return phonetic_code;
  }

  public void setPhonetic_code(String phonetic_code) {
    this.phonetic_code = phonetic_code;
  }
}
