package com.example.hibernatedemo.custom.deserializer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("custom/deserialize")
public class CustomDeserializController {
    @PostMapping
    public AppointmentDTO convert(@RequestBody  AppointmentGetDTO appointmentDTO) {
        System.out.println(appointmentDTO.getStartDate());
        System.out.println(appointmentDTO.getEndDate());
        return appointmentDTO;
    }
}
