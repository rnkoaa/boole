package com.my.db;

public class Movie_info_idx {
  private Long id;
  private Long movie_id;
  private Long info_type_id;
  private String info;
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

  public Long getInfo_type_id() {
    return info_type_id;
  }

  public void setInfo_type_id(Long info_type_id) {
    this.info_type_id = info_type_id;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
