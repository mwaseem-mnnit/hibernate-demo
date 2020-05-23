package com.example.hibernatedemo.custom.deserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

@JsonDeserialize(using= JacksonInstantDeserializer.class)
public class AppointmentGetDTO extends AppointmentDTO {
    public AppointmentGetDTO(Instant s, Instant e) {
        super(s,e);
    }
}
