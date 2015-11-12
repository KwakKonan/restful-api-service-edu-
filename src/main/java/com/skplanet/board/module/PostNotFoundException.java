package com.skplanet.board.module;

import com.skplanet.common.ResourceNotFoundException;

public class PostNotFoundException extends ResourceNotFoundException {

  private final long postId;

  public PostNotFoundException(long postId) {
    super(String.format("%s번 게시물을 찾을 수 없습니다.", postId));
    this.postId = postId;
  }

  public long getPostId() {
    return postId;
  }
  
  @Override
  public String[] getCodes() {
    return new String[] { "error.postNotFound" };
  }

  @Override
  public Object[] getArguments() {
    return new Object[] { postId };
  }
  
}
