package com.skplanet.board;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class PostTest {

  private Post target;
  
  @Before
  public void setUp() {
    this.target = new Post("test post", "", "arawn", "password", mock(Board.class));
  }
  
  @Test
  public void 비밀번호가_일치하면_수정된다() {
    String editTitle = "edit post";
    target.edit(editTitle, "", "arawn", "password");
    
    assertThat(target.getTitle(), is(editTitle));
  }
  
  @Test(expected=BadPasswordException.class)
  public void 비밀번호가_다르면_예외가_발생한다() {
    target.edit("edit post", "", "arawn", "bad_password");
  }
  
}
