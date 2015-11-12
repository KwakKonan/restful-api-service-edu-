package com.skplanet.board.module;

import com.skplanet.common.ResourceNotFoundException;

public class BoardNameNotFoundException extends ResourceNotFoundException {

  private final String boardName;

  public BoardNameNotFoundException(String boardName) {
    super(String.format("%s 게시판을 찾을 수 없습니다.", boardName));
    this.boardName = boardName;
  }

  public String getBoardName() {
    return boardName;
  }
  
  @Override
  public String[] getCodes() {
    return new String[] { "error.boardNameNotFound" };
  }

  @Override
  public Object[] getArguments() {
    return new Object[] { boardName };
  }  

}
