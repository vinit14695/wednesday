package com.wednesday.assignment.relaxicab.data.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    @Size(max = 30)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 30)
    @NotNull
    private String lastName;

    @Column(name = "username")
    @Size(max = 30)
    @NotNull
    private String userName;

    @Column(name = "email")
    @Size(max = 50)
    @NotNull
    private String email;

    @Column(name = "password")
    @Size(max = 255)
    @ToStringExclude
    @NotNull
    private String password;

    @Size(max = 200)
    @ToStringExclude
    @NotNull
    private String salt;

    @Column(name = "country")
    @Size(max = 30)
    private String country;

    @Column(name = "dob")
    @Size(max = 30)
    private String dob;

    @Column(name = "role")
    private String role;

    @Column(name = "contact_number")
    @Size(max = 30)
    private String contactNumber;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trip> trips;
}
