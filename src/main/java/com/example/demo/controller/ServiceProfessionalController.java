package com.example.demo.controller;

import com.example.demo.model.ServiceProfessional;
import com.example.demo.model.TimeSlot;
import com.example.demo.service.ServiceProfessionalService;
import com.example.demo.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
public class ServiceProfessionalController {

    @Autowired
    private ServiceProfessionalService serviceProfessionalService;
    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping("/serviceProfessionals")
    public ServiceProfessional getServiceProfessional(@RequestParam(value = "id") Integer id) {
        return serviceProfessionalService.getServiceProfessionalById(id);
    }

    @GetMapping(value = "/allserviceprofessionals")
    public List<ServiceProfessional> getServiceProfessional() {
        return serviceProfessionalService.getServiceProfessional();
    }

    @GetMapping(value = "/getServiceProfessionalFromCategory")
    public List<ServiceProfessional> getServiceProfessionalFromCategory(
            @RequestParam(value = "service_subcategory") String subCategory) {
        return serviceProfessionalService.getServiceProfessionalFromSubcategory(subCategory);
    }

    @PostMapping(value = "/saveserviceProfessional")
    public ResponseEntity<String> save(@RequestBody ServiceProfessional serviceprofessional){
        try {
            serviceProfessionalService.save(serviceprofessional);
            List<ServiceProfessional> professional = serviceProfessionalService.getServiceProfessionalByPhoneNumberAndSubcategory(
                    serviceprofessional.getServiceSubcategory(), serviceprofessional.getPhoneNumber());
            LocalDateTime dateTime = LocalDateTime.of(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth(), 8, 0);
            dateTime = dateTime.plusDays(1);
            List<TimeSlot> timeSlots = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 10; j++) {
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setDateTime(dateTime);
                    timeSlot.setBooked(false);
                    timeSlot.setServiceProfessional(professional.get(0));

                    timeSlots.add(timeSlot);
                    dateTime = dateTime.plusHours(1);
                }
                dateTime = dateTime.plusHours(14);
            }
            timeSlotService.insertTimeSlots(timeSlots);
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
