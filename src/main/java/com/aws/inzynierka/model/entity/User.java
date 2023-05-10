package com.aws.inzynierka.model.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true, nullable = false)
    private UUID userUuid = UUID.randomUUID();
    @NotBlank
    @Length(min = 3, max = 50)
    @Column(unique = true, nullable = false)
    private String username;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    private Boolean isCertified;
    private Boolean isEnabled;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email, Boolean isCertified, Boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isCertified = isCertified;
        this.isEnabled = isEnabled;
    }
}
