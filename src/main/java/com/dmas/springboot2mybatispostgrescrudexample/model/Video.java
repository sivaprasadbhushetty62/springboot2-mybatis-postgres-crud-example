package com.dmas.springboot2mybatispostgrescrudexample.model;

public class Video {

  private Long id;
  private String title;
  private String url;
  private String description;

  public Video() { }

  public Video(String title, String url, String description) {
    this.title = title;
    this.url = url;
    this.description = description;
  }

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


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Employee [id=" + this.id + ", title=" + this.title + ", url=" + this.url + ", description=" + this.description
    + "]";
  }

}
