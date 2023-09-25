package com.example.demo.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter// Automatically generate getters and setters
@Setter
@AllArgsConstructor // Generate constructors
@NoArgsConstructor
@Table(name = "service_professional")
@Entity // Tells Hibernate to make a table out of this class
public class ServiceProfessional {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_professional_name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "service_name", length = 100)
    private String serviceName;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "service_subcategory", nullable = false, length = 100)
    private String serviceSubcategory;
}
