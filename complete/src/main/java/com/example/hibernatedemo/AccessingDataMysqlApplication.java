package com.example.hibernatedemo;

import com.example.hibernatedemo.custom.deserializer.AppointmentGetDTO;
import com.example.hibernatedemo.custom.deserializer.JacksonInstantDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableFeignClients
public class AccessingDataMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMysqlApplication.class, args);
    }

    @Bean
    public Module dynamoDemoEntityDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(AppointmentGetDTO.class, new JacksonInstantDeserializer());
        return module;
    }
}
