package com.skplanet.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String content;
  private String author;
  private String password;
  private Date createdDate;
  private Date lastModifiedDate;
  
  @ManyToOne(optional=false)
  private Board board;

  protected Post() { }
  
  public Post(String title, String content, String author, String password, Board board) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.password = password;
    this.board = board;

    Date now = new Date();
    this.createdDate = now;
    this.lastModifiedDate = now;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getAuthor() {
    return author;
  }

  public String getPassword() {
    return password;
  }

  public Board getBoard() {
    return board;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public Post edit(String title, String content, String author, String password) {
    if (!this.password.equals(password)) {
      throw new BadPasswordException();
    }

    this.title = title;
    this.content = content;
    this.author = author;

    return this;
  }

}
