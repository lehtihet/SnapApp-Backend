package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import javax.transaction.Transactional;

@Transactional
@RestController
public class CustomerController {

    @Autowired private CustomerService customerService;
    @Autowired private TimeSlotService timeSlotService;

    @GetMapping("/customers")
    public Customer getCustomer(@RequestParam(value = "id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customers-all")
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping("/customers-post")
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        try {
            customerService.save(customer);
            timeSlotService.updateTimeSlotBooked(customer.getTimeSlot().getId(), true);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        
    }
}
