package com.my.db;

public class Title {
  private Long id;
  private String title;
  private String imdb_index;
  private Long kind_id;
  private Long production_year;
  private Long imdb_id;
  private String phonetic_code;
  private Long episode_of_id;
  private Long season_nr;
  private Long episode_nr;
  private String series_years;
  private String md5sum;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImdb_index() {
    return imdb_index;
  }

  public void setImdb_index(String imdb_index) {
    this.imdb_index = imdb_index;
  }

  public Long getKind_id() {
    return kind_id;
  }

  public void setKind_id(Long kind_id) {
    this.kind_id = kind_id;
  }

  public Long getProduction_year() {
    return production_year;
  }

  public void setProduction_year(Long production_year) {
    this.production_year = production_year;
  }

  public Long getImdb_id() {
    return imdb_id;
  }

  public void setImdb_id(Long imdb_id) {
    this.imdb_id = imdb_id;
  }

  public String getPhonetic_code() {
    return phonetic_code;
  }

  public void setPhonetic_code(String phonetic_code) {
    this.phonetic_code = phonetic_code;
  }

  public Long getEpisode_of_id() {
    return episode_of_id;
  }

  public void setEpisode_of_id(Long episode_of_id) {
    this.episode_of_id = episode_of_id;
  }

  public Long getSeason_nr() {
    return season_nr;
  }

  public void setSeason_nr(Long season_nr) {
    this.season_nr = season_nr;
  }

  public Long getEpisode_nr() {
    return episode_nr;
  }

  public void setEpisode_nr(Long episode_nr) {
    this.episode_nr = episode_nr;
  }

  public String getSeries_years() {
    return series_years;
  }

  public void setSeries_years(String series_years) {
    this.series_years = series_years;
  }

  public String getMd5sum() {
    return md5sum;
  }

  public void setMd5sum(String md5sum) {
    this.md5sum = md5sum;
  }
}
