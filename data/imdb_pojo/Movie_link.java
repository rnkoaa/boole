package com.my.db;

public class Movie_link {
  private Long id;
  private Long movie_id;
  private Long linked_movie_id;
  private Long link_type_id;

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

  public Long getLinked_movie_id() {
    return linked_movie_id;
  }

  public void setLinked_movie_id(Long linked_movie_id) {
    this.linked_movie_id = linked_movie_id;
  }

  public Long getLink_type_id() {
    return link_type_id;
  }

  public void setLink_type_id(Long link_type_id) {
    this.link_type_id = link_type_id;
  }
}
