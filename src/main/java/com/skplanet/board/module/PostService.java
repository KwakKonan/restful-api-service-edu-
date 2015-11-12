package com.skplanet.board.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skplanet.board.Board;
import com.skplanet.board.Post;

@Service
public class PostService {

  private PostRepository postRepository;

  public List<Post> findPosts(Board board) {
    return postRepository.findByBoard(board);
  }

  public Post writePost(WritePostForm form) {
    return postRepository.save(new Post(form.getTitle(), form.getContent(), form.getAuthor(), form.getPassword(), form.getBoard()));
  }

  public Post findPost(long postId) {
    return postRepository.findById(postId)
                         .orElseThrow(() -> new PostNotFoundException(postId));
  }

  public Post editPost(EditPostForm form) {
    Post origin = findPost(form.getId());

    return origin.edit(form.getTitle(), form.getContent(), form.getAuthor(), form.getPassword());
  }

  public Post deletePost(Long postId) {
    Post origin = findPost(postId);

    postRepository.delete(origin);

    return origin;
  }

  @Autowired
  public void setPostRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public static interface WritePostForm {

    String getTitle();
    String getContent();
    String getAuthor();
    String getPassword();
    Board getBoard();

  }

  public static interface EditPostForm {

    Long getId();
    String getTitle();
    String getContent();
    String getAuthor();
    String getPassword();

  }

}
