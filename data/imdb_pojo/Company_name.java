package com.my.db;

public class Company_name {
  private Long id;
  private String name;
  private String country_code;
  private Long imdb_id;
  private String name_pcode_nf;
  private String name_pcode_sf;
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

  public String getCountry_code() {
    return country_code;
  }

  public void setCountry_code(String country_code) {
    this.country_code = country_code;
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

  public String getName_pcode_sf() {
    return name_pcode_sf;
  }

  public void setName_pcode_sf(String name_pcode_sf) {
    this.name_pcode_sf = name_pcode_sf;
  }

  public String getMd5sum() {
    return md5sum;
  }

  public void setMd5sum(String md5sum) {
    this.md5sum = md5sum;
  }
}
