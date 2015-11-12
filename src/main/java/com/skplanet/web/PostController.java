package com.skplanet.web;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skplanet.board.Board;
import com.skplanet.board.Post;
import com.skplanet.board.module.BoardService;
import com.skplanet.board.module.PostService;
import com.skplanet.board.module.PostService.EditPostForm;
import com.skplanet.web.dto.PostRequest;
import com.skplanet.web.dto.PostResponse;
import com.skplanet.web.dto.PostResponse.PostListModel;
import com.skplanet.web.support.ErrorResponseEntity;

@RestController
@RequestMapping(value = "/boards/{boardName}/posts")
public class PostController {

  private BoardService boardService;
  private PostService postService;
  private MessageSource messageSource;
  private SpringValidatorAdapter validator;

  @RequestMapping(method = RequestMethod.GET)
  public List<PostListModel> listPosts(@PathVariable String boardName) {
    Board board = boardService.findBoard(boardName);    
    return postService.findPosts(board)
                      .stream()
                      .map(PostListModel::new)
                      .collect(Collectors.toList());
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public PostResponse.PostModel writePost( @PathVariable String boardName
                                         , @RequestBody @Valid PostRequest writeForm) {
      writeForm.setBoard(boardService.findBoard(boardName));
      
      return new PostResponse.PostModel(postService.writePost(writeForm));
  }  
  
  @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
  public PostResponse.PostModel viewPost( @PathVariable String boardName
                                        , @PathVariable Long postId) {

    Board board = boardService.findBoard(boardName);
    Post findPost = postService.findPost(postId);

    return new PostResponse.PostModel(findPost);
  }
  
  @RequestMapping(value = "/{postId}", method = RequestMethod.PUT)
  public PostResponse.PostModel editPost( @PathVariable String boardName
                                        , @PathVariable Long postId
                                        , @RequestBody PostRequest editForm
                                        , BindingResult bindingResult) throws BindException {
    // editForm.setPostId(postId);
    
    validator.validate(editForm, bindingResult);
    validator.validate(editForm, bindingResult, EditPostForm.class);
    if (bindingResult.hasErrors()) {
      throw new BindException(bindingResult);
    }
    
    Board board = boardService.findBoard(boardName);
    Post editPost = postService.editPost(editForm);

    return new PostResponse.PostModel(editPost);
  }
  
  @RequestMapping(value = "/{postId}", method = RequestMethod.DELETE)
  public PostResponse.PostModel deletePost( @PathVariable String boardName
                                          , @PathVariable Long postId) {
    Board board = boardService.findBoard(boardName);
    Post deletePost = postService.deletePost(postId);

    return new PostResponse.PostModel(deletePost);
  }  
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException exception, Locale locale) {
    List<String> bindingResults = exception.getBindingResult()
                                           .getAllErrors()
                                           .stream()
                                           .map(error -> messageSource.getMessage(error, locale))
                                           .collect(Collectors.toList());
    
    return ErrorResponseEntity.badReqeust("입력값이 올바르지 않습니다.", bindingResults);
  }
  
  @ExceptionHandler(BindException.class)
  public ErrorResponseEntity bindException(BindException exception, Locale locale) {
    List<String> bindingResults = exception.getBindingResult()
                                           .getAllErrors()
                                           .stream()
                                           .map(error -> messageSource.getMessage(error, locale))
                                           .collect(Collectors.toList());
    
    return ErrorResponseEntity.badReqeust("입력값이 올바르지 않습니다.", bindingResults);
  }  

  @Autowired
  public void setBoardService(BoardService boardService) {
    this.boardService = boardService;
  }

  @Autowired
  public void setPostService(PostService postService) {
    this.postService = postService;
  }
  
  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Autowired
  public void setValidator(Validator validator) {
    this.validator = new SpringValidatorAdapter(validator);
  }

}
