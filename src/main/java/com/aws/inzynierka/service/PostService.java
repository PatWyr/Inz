package com.aws.inzynierka.service;

import com.aws.inzynierka.model.entity.CertificationType;
import com.aws.inzynierka.model.entity.Post;
import com.aws.inzynierka.model.entity.User;
import com.aws.inzynierka.model.request.PostRequest;
import com.aws.inzynierka.model.response.PostResponse;
import com.aws.inzynierka.repository.CertificationRepository;
import com.aws.inzynierka.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CertificationRepository certificationRepository;
    private final AuthorizationService authorizationService;

    public void createPost(PostRequest postRequest) {
        User user = authorizationService.getCurrentUser();
        CertificationType certification = certificationRepository.findCertificationTypeByName(postRequest.getCertificationName());
        postRepository.save(Post.builder()
                .user(user)
                .certificationType(certification)
                .postName(postRequest.getPostName())
                .description(postRequest.getDescription())
                .isQuestion(postRequest.getIsQuestion())
                .createdDate(Instant.now())
                .build());
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(post ->
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
        ).collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return PostResponse
                .builder()
                .id(Math.toIntExact(post.getPostId()))
                .postName(post.getPostName())
                .isQuestion(post.getIsQuestion())
                .description(post.getDescription())
                .userName(post.getUser().getUsername())
                .duration(post.getCreatedDate().toString())
                .certificationName(post.getCertificationType().getName())
                .build();
    }


}
