package com.skplanet.web.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.skplanet.board.Board;
import com.skplanet.board.module.PostService.EditPostForm;
import com.skplanet.board.module.PostService.WritePostForm;;

public final class PostRequest implements WritePostForm, EditPostForm {

  @NotNull(groups={ EditPostForm.class })
  private Long id;
  
  @NotEmpty
  private String title;
  
  @NotEmpty
  private String content;
  
  @NotEmpty
  private String author;
  
  @NotEmpty
  private String password;
  
  private Board board;

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
  public void setPostId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  @Override
  public String toString() {
    return "PostRequest [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", password="
        + password + ", board=" + board + "]";
  }

}
