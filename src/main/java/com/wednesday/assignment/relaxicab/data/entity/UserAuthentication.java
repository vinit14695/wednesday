package com.wednesday.assignment.relaxicab.data.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "user_auth")
public class UserAuthentication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "access_token")
    @Size(max = 500)
    @NotNull
    private String accessToken;

    @Column(name = "expires_at")
    @NotNull
    private Timestamp expiresAt;

    @Column(name = "logout_at")
    private Timestamp logoutAt;

    @Column(name = "login_at")
    @NotNull
    private Timestamp loginAt;
}
