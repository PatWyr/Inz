package com.aws.inzynierka;

import com.aws.inzynierka.model.entity.Post;
import com.aws.inzynierka.model.entity.User;
import com.aws.inzynierka.repository.PostRepository;
import com.aws.inzynierka.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PostTest {
    @Autowired
    WebClient webClient;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    static User testUser = new User("test", "test", "test@test.pl", false, true);

    static Post testPost = new Post(null, "Test", false, "Test", 1, Instant.now(), 0, null, null);


    @BeforeAll
    void prepareTests() {
        userRepository.save(testUser);
    }

    @AfterAll
    void cleanUp() {
        postRepository.delete(testPost);
        userRepository.save(testUser);
    }


    @Test
    public void shouldReturnPostById() {
        // given
        userRepository.save(testUser);
        // when
        Post testPost = webClient
                .get()
                .uri("/api/v1/post/1000")
                .retrieve()
                .bodyToMono(Post.class)
                .block();
        // then
        Assertions.assertEquals(1000L, testPost.getPostId());
        Assertions.assertEquals("Test", testPost.getPostName());
        Assertions.assertEquals("Test", testPost.getDescription());
    }

}
