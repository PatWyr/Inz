package com.aws.inzynierka.controller;

import com.aws.inzynierka.model.dto.CommentsDto;
import com.aws.inzynierka.model.entity.Comment;
import com.aws.inzynierka.model.entity.Post;
import com.aws.inzynierka.model.response.CommentResponse;
import com.aws.inzynierka.model.response.PostResponse;
import com.aws.inzynierka.repository.CommentRepository;
import com.aws.inzynierka.repository.PostRepository;
import com.aws.inzynierka.service.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/comment")
@Log4j2
public class CommentController {


    @Autowired(required = true)
    private CommentRepository commentRepository;
    @Autowired(required = true)
    private PostRepository postRepository;
    @Autowired(required = true)
    private AuthorizationService authorizationService;

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentResponse>> receiveCommentsForPost(@PathVariable String id) {
        log.info("Retriving comments for post with id: " + id);
        List<CommentResponse> toReturn = commentRepository.findAllByPost(postRepository.findById(Long.valueOf(id)).get()).stream().map(
                comment -> CommentResponse
                        .builder()
                        .postId(comment.getPost().getPostId().toString())
                        .duration(comment.getCreatedDate().toString())
                        .text(comment.getText())
                        .username(comment.getUser().getUsername())
                        .build()
        ).collect(Collectors.toList());
        return ResponseEntity.ok(toReturn);
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new RuntimeException(commentsDto.getPostId().toString()));
        Comment comment = Comment
                .builder()
                .createdDate(Instant.now())
                .post(post)
                .text(commentsDto.getText())
                .user(authorizationService.getCurrentUser())
                .build();
        commentRepository.save(comment);
    }
}
