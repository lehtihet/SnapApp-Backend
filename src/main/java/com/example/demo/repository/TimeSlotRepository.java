package com.example.demo.repository;

import com.example.demo.model.TimeSlot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer> {

    @Query(value = "SELECT * FROM time_slot WHERE ?1 < date_time AND date_time < ?2 AND service_professional_id = ?3", nativeQuery = true)
    List<TimeSlot> findTimeSlotsInRange(LocalDate startDate, LocalDate endDate, Integer serviceProfessionalId);

    @Query(value = "SELECT * FROM time_slot where ?1 < date_time and date_time < ?2 and service_professional_id = ?3 and booked = false", countQuery = "select 1", nativeQuery = true)
    List<TimeSlot> findFreeTimeSlots(LocalDate startDate, LocalDate endDate, Integer serviceProfessionalId);

    @Modifying
    @Query(value = "UPDATE time_slot SET booked = :booked WHERE id = :id and booked != :booked", countQuery = "select 1", nativeQuery = true)
    int updateTimeSlotBooked(@Param("id") Integer id, @Param("booked") Boolean booked);
}
