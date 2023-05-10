package com.aws.inzynierka.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostResponse {
    private Integer id;
    private String postName;
    private String url;
    private String description;
    private Boolean isQuestion;
    private String userName;
    private String certificationName;
    private String duration;
}
