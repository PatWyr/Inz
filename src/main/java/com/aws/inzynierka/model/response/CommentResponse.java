package com.aws.inzynierka.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponse {
    String text;
    String postId;
    String username;
    String duration;
}
