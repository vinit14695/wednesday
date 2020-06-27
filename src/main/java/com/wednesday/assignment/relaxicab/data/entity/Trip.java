package com.wednesday.assignment.relaxicab.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "from_location_id")
    private Location from;

    @OneToOne
    @JoinColumn(name = "to_location_id")
    private Location to;

    @Column(name = "booked_on")
    private Timestamp bookedOn;

    @Column(name = "started_on")
    private Timestamp startedOn;

    @Column(name = "completed_on")
    private Timestamp completedOn;

    private String status;

    private long distance;

    @NotNull
    private double amount;

}
