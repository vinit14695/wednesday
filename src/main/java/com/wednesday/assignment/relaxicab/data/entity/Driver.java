package com.wednesday.assignment.relaxicab.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @NotNull
    private String userName;

    @Column(name = "cab_number")
    private String cabNumber;

    @Column(name = "contact_number")
    @NotNull
    private String contactNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_location_id")
    private Location currentLocation;

    private boolean available;

//    @OneToMany(
//            mappedBy = "driver",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Trip> trips;
}
