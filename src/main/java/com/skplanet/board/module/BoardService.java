package com.skplanet.board.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skplanet.board.Board;

@Service
public class BoardService {

  private BoardRepository boardRepository;

  public List<Board> findBoards() {
    return boardRepository.findAll();
  }

  public Board findBoard(String boardName) {
    return boardRepository.findByName(boardName)
                          .orElseThrow(() -> new BoardNameNotFoundException(boardName));
  }

  @Autowired
  public void setBoardRepository(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

}
