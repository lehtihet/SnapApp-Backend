package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter// Automatically generate getters and setters
@Setter
@AllArgsConstructor // Generate constructors
@NoArgsConstructor
@Table(name = "time_slot")
@Entity // Tells Hibernate to make a table out of this class
public class TimeSlot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "booked", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean booked;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_professional_id")
    private ServiceProfessional serviceProfessional;
}
