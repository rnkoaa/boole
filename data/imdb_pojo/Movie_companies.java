package com.my.db;

public class Movie_companies {
  private Long id;
  private Long movie_id;
  private Long company_id;
  private Long company_type_id;
  private String note;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMovie_id() {
    return movie_id;
  }

  public void setMovie_id(Long movie_id) {
    this.movie_id = movie_id;
  }

  public Long getCompany_id() {
    return company_id;
  }

  public void setCompany_id(Long company_id) {
    this.company_id = company_id;
  }

  public Long getCompany_type_id() {
    return company_type_id;
  }

  public void setCompany_type_id(Long company_type_id) {
    this.company_type_id = company_type_id;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
