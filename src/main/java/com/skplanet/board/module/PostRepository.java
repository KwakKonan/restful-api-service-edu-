package com.skplanet.board.module;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skplanet.board.Board;
import com.skplanet.board.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

  Optional<Post> findById(long postId);
  List<Post> findByBoard(Board board);

}
