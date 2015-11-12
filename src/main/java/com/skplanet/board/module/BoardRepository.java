package com.skplanet.board.module;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skplanet.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>
                                       , JpaSpecificationExecutor<Board> {

  Optional<Board> findByName(String boardName);

}
