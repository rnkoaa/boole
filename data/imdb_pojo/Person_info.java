package com.my.db;

public class Person_info {
  private Long id;
  private Long person_id;
  private Long info_type_id;
  private String info;
  private String note;

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
