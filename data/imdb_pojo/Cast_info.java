package com.my.db;

public class Cast_info {
  private Long id;
  private Long person_id;
  private Long movie_id;
  private Long person_role_id;
  private String note;
  private Long nr_order;
  private Long role_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPerson_id() {
    return person_id;
  }

  public void setPerson_id(Long person_id) {
    this.person_id = person_id;
  }

  public Long getMovie_id() {
    return movie_id;
  }

  public void setMovie_id(Long movie_id) {
    this.movie_id = movie_id;
  }

  public Long getPerson_role_id() {
    return person_role_id;
  }

  public void setPerson_role_id(Long person_role_id) {
    this.person_role_id = person_role_id;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Long getNr_order() {
    return nr_order;
  }

  public void setNr_order(Long nr_order) {
    this.nr_order = nr_order;
  }

  public Long getRole_id() {
    return role_id;
  }

  public void setRole_id(Long role_id) {
    this.role_id = role_id;
  }
}
