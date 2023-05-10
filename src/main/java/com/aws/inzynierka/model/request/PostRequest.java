package com.aws.inzynierka.model.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private String postName;
    private String certificationName;
    private String description;
    private Boolean isQuestion;
}
