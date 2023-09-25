package com.example.demo.service;

import com.example.demo.model.ServiceProfessional;
import com.example.demo.repository.ServiceProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceProfessionalService {

    @Autowired
    private ServiceProfessionalRepository serviceProfessionalRepository;

    public ServiceProfessional getServiceProfessionalById(Integer id) {
        return serviceProfessionalRepository.findById(id).get();
    }

    public void save(ServiceProfessional serviceprofessional) {
        serviceProfessionalRepository.save(serviceprofessional);
    }

    public List<ServiceProfessional> getServiceProfessional() {
        return serviceProfessionalRepository.findAll();
    }

    public List<ServiceProfessional> getServiceProfessionalFromSubcategory(String subCategory) {
//        return serviceProfessionalRepository.findAll();
        return serviceProfessionalRepository.findByServiceSubcategory(subCategory);
    }

    public List<ServiceProfessional> getServiceProfessionalByPhoneNumberAndSubcategory(String serviceSubcategory,
                                                                                       String phoneNumber) {
        return serviceProfessionalRepository.findByPhoneNumberAndSubcategory(serviceSubcategory, phoneNumber);
    }
}
