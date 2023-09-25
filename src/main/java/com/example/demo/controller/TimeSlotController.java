package com.example.demo.controller;

import com.example.demo.model.TimeSlot;
import com.example.demo.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@RestController
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    /* Returns the time slots between a range of dates for a given service professional */
    @GetMapping("/timeSlots")
    public List<TimeSlot> getTimeSlotsInRange(@RequestParam(value = "startDate") LocalDate startDate,
                                              @RequestParam(value = "endDate") LocalDate endDate,
                                              @RequestParam(value = "serviceProfessionalId") Integer serviceProfessionalId) {
        return timeSlotService.getTimeSlotsInRange(startDate, endDate, serviceProfessionalId);
    }

    @GetMapping("/freeTimeSlots")
    public List<TimeSlot> getFreeTimeSlots(@RequestParam(value = "date") LocalDate date,
                                           @RequestParam(value = "serviceProfessionalId") Integer serviceProfessionalId) {
        return timeSlotService.getFreeTimeSlots(date, serviceProfessionalId);
    }

    /* Updates the booked field of the given time slot.
    Returns conflict if booked is the same as the value already in the database */
    @PutMapping("/timeSlots/{id}")
    public ResponseEntity<String> updateTimeSlotBooked(@PathVariable("id") Integer id, @RequestParam(value = "booked") Boolean booked) {
        int i = timeSlotService.updateTimeSlotBooked(id, booked);
        if (i == 1 && booked) {
            return new ResponseEntity<>("Updated time slot to booked.", HttpStatus.OK);
        } else if (i == 1) {
            return new ResponseEntity<>("Updated time slot to not booked.", HttpStatus.OK);
        } else if (i == 0 && booked) {
            return new ResponseEntity<>("Cannot book a time slot that is already booked. Booked is already set to true.", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Cannot set booked to false when it is already false.", HttpStatus.CONFLICT);
        }
    }
}
