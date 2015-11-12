package com.my.db;

public class Movie_keyword {
  private Long id;
  private Long movie_id;
  private Long keyword_id;

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

  public Long getKeyword_id() {
    return keyword_id;
  }

  public void setKeyword_id(Long keyword_id) {
    this.keyword_id = keyword_id;
  }
}
