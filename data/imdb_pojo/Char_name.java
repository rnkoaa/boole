package com.my.db;

public class Char_name {
  private Long id;
  private String name;
  private String imdb_index;
  private Long imdb_id;
  private String name_pcode_nf;
  private String surname_pcode;
  private String md5sum;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImdb_index() {
    return imdb_index;
  }

  public void setImdb_index(String imdb_index) {
    this.imdb_index = imdb_index;
  }

  public Long getImdb_id() {
    return imdb_id;
  }

  public void setImdb_id(Long imdb_id) {
    this.imdb_id = imdb_id;
  }

  public String getName_pcode_nf() {
    return name_pcode_nf;
  }

  public void setName_pcode_nf(String name_pcode_nf) {
    this.name_pcode_nf = name_pcode_nf;
  }

  public String getSurname_pcode() {
    return surname_pcode;
  }

  public void setSurname_pcode(String surname_pcode) {
    this.surname_pcode = surname_pcode;
  }

  public String getMd5sum() {
    return md5sum;
  }

  public void setMd5sum(String md5sum) {
    this.md5sum = md5sum;
  }
}
