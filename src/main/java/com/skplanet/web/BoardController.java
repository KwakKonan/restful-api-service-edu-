package com.skplanet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skplanet.board.Board;
import com.skplanet.board.module.BoardService;

@RestController
@RequestMapping(value = "/boards")
public class BoardController {

  private BoardService boardService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Board> listBoards() {
    return boardService.findBoards();
  }
  
  @RequestMapping(value = "/{boardName}/info", method = RequestMethod.GET)
  public Board findBoard(@PathVariable String boardName) {
    return boardService.findBoard(boardName);
  }
  
  @Autowired
  public void setBoardService(BoardService boardService) {
    this.boardService = boardService;
  }

}
