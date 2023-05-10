package com.aws.inzynierka.controller;

import com.aws.inzynierka.model.entity.Post;
import com.aws.inzynierka.model.request.PostRequest;
import com.aws.inzynierka.model.response.PostResponse;
import com.aws.inzynierka.repository.PostRepository;
import com.aws.inzynierka.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/list-posts")
    public ResponseEntity<List<PostResponse>> receiveAllPosts() {
        log.info("Listing all posts");
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/create-post")
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        log.info("Creating new post");
        postService.createPost(postRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        log.info("Getting post with id: " + id);
        PostResponse ret = postRepository.findById(Long.valueOf(id)).map(post ->
                PostResponse
                        .builder()
                        .id(Math.toIntExact(post.getPostId()))
                        .postName(post.getPostName())
                        .isQuestion(post.getIsQuestion())
                        .description(post.getDescription())
                        .userName(post.getUser().getUsername())
                        .duration(post.getCreatedDate().toString())
                        .certificationName(post.getCertificationType().getName())
                        .build()

        ).get();
        return ResponseEntity.ok(ret);
    }
}
