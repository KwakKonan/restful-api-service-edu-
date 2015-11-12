package com.skplanet.board.module;

import static org.junit.Assert.*;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skplanet.board.Board;
import com.skplanet.board.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BoardModuleTestConfig.class)
public class PostServiceTest {
  
  @Autowired
  private PostService postService;
  
  @Test
  public void 특정_게시판에_등록된_모든_게시물을_조회한다() {
    assertThat(postService.findPosts(new Board("general")).size(), is(1));
    assertThat(postService.findPosts(new Board("random")).size(), is(0));
  }
  
  @Test
  public void 게시물_CRUD_기능_확인() {
    Board testBoard = new Board(UUID.randomUUID().toString());
    
    Post writePost = postService.writePost(new PostService.WritePostForm() {
      public Board getBoard() {
        return testBoard;
      }
      public String getTitle() {
        return "write title";
      }
      public String getContent() {
        return "";
      }
      public String getAuthor() {
        return "";
      }
      public String getPassword() {
        return "";
      }
    });
    
    assertThat(postService.findPosts(testBoard).size(), is(1));
    assertThat(postService.findPost(writePost.getId()).getTitle(), is(writePost.getTitle()));
    
    Post editPost = postService.editPost(new PostService.EditPostForm() {
      public Long getId() {
        return writePost.getId();
      }
      public String getTitle() {
        return "edit title";
      }
      public String getContent() {
        return "";
      }
      public String getPassword() {
        return "";
      }
      public String getAuthor() {
        return "";
      }
    });
    
    assertThat(postService.findPosts(testBoard).size(), is(1));
    assertThat(postService.findPost(editPost.getId()).getTitle(), is(editPost.getTitle()));
    
    postService.deletePost(writePost.getId());
    
    assertThat(postService.findPosts(testBoard).size(), is(0));    
  }
  
  @Test(expected=PostNotFoundException.class)
  public void 게시물을_찾지_못하면_예외가_발생한다() {
    postService.findPost(Integer.MAX_VALUE);
  }

}
