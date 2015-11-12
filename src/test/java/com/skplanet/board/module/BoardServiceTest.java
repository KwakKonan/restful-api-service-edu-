package com.skplanet.board.module;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skplanet.board.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BoardModuleTestConfig.class)
public class BoardServiceTest {
  
  @Autowired
  private BoardService boardService;
  
  @Test
  public void 모든_게시판을_조회한다() {
    assertThat(boardService.findBoards().size(), is(2));
  }
  
  @Test
  public void 하나의_게시판을_조회한다() {
    assertThat(boardService.findBoard("general"), is(new Board("general")));
  }
  
  @Test(expected=BoardNameNotFoundException.class)
  public void 게시판을_찾지_못하면_예외가_발생한다() {
    boardService.findBoard("no_board");
  }

}
