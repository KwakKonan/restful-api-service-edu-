package com.skplanet.board;

import com.skplanet.common.BusinessException;

public class BadPasswordException extends BusinessException {

  public BadPasswordException() {
    super("게시물의 비밀번호가 일치하지 않습니다.");
  }

  @Override
  public String[] getCodes() {
    return new String[]{ "error.badPassword" };
  }

}