package com.aws.inzynierka.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String postName;

    @Column(nullable = false)
    private Boolean isQuestion;

    @Column(nullable = false)
    private String description;

    private Integer voteCount = 0;

    @Column(nullable = false)
    private Instant createdDate;

    @Version
    private int version;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private CertificationType certificationType;
}
