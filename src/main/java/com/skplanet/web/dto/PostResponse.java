package com.skplanet.web.dto;

import java.util.Date;

import com.skplanet.board.Board;
import com.skplanet.board.Post;

public final class PostResponse {

  public static class PostListModel {

    private final Post origin;

    public PostListModel(Post origin) {
      this.origin = origin;
    }

    public Long getId() {
      return origin.getId();
    }
    
    public String getTitle() {
      return origin.getTitle();
    }

    public String getAuthor() {
      return origin.getAuthor();
    }

    public Date getLastModifiedDate() {
      return origin.getLastModifiedDate();
    }

  }

  public static class PostModel {

    private final Post origin;

    public PostModel(Post origin) {
      this.origin = origin;
    }

    public Long getId() {
      return origin.getId();
    }

    public String getTitle() {
      return origin.getTitle();
    }

    public String getContent() {
      return origin.getContent();
    }

    public String getAuthor() {
      return origin.getAuthor();
    }

    public Board getBoard() {
      return origin.getBoard();
    }

    public Date getCreatedDate() {
      return origin.getCreatedDate();
    }

    public Date getLastModifiedDate() {
      return origin.getLastModifiedDate();
    }

  }

}
