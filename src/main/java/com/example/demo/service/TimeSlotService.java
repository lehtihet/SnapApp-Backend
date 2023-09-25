package com.example.demo.service;

import com.example.demo.model.TimeSlot;
import com.example.demo.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public TimeSlot getTimeSlotById(Integer id) {
        return timeSlotRepository.findById(id).get();
    }

    public List<TimeSlot> getTimeSlotsInRange(LocalDate startDate, LocalDate endDate, Integer serviceProfessionalId) {
        return timeSlotRepository.findTimeSlotsInRange(startDate, endDate, serviceProfessionalId);
    }

    public int updateTimeSlotBooked(Integer id, Boolean booked) {
        return timeSlotRepository.updateTimeSlotBooked(id, booked);
    }

    public List<TimeSlot> getFreeTimeSlots(LocalDate date, Integer serviceProfessionalId) {
        return timeSlotRepository.findFreeTimeSlots(date, date.plusDays(1), serviceProfessionalId);
    }

    public void insertTimeSlots(List<TimeSlot> timeSlots) {
        timeSlotRepository.saveAll(timeSlots);
    }
}
