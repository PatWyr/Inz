package com.aws.inzynierka.repository;

import com.aws.inzynierka.model.entity.Comment;
import com.aws.inzynierka.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
