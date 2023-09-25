package com.example.demo.repository;

import com.example.demo.model.ServiceProfessional;
import com.example.demo.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceProfessionalRepository extends JpaRepository<ServiceProfessional, Integer> {

    @Query(value = "SELECT s FROM ServiceProfessional s WHERE s.serviceSubcategory = :subCategory")
    List<ServiceProfessional> findByServiceSubcategory(@Param("subCategory") String subCategory);

    @Query(value = "SELECT * FROM service_professional WHERE phone_number = ?2 " +
            "AND service_subcategory = ?1 ", nativeQuery = true)
    List<ServiceProfessional> findByPhoneNumberAndSubcategory(String serviceSubcategory, String phoneNumber);
}
