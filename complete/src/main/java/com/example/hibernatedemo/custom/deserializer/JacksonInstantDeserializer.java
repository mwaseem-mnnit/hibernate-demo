package com.example.hibernatedemo.custom.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;

public class JacksonInstantDeserializer extends StdDeserializer<AppointmentGetDTO> {
    public JacksonInstantDeserializer() { this(AppointmentDTO.class); }
    public JacksonInstantDeserializer(Class<?> clazz) { super(clazz); }

    @Override
    public AppointmentGetDTO deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        Instant s=null;
        Instant e=null;
        if(node.get("start_date") != null) {
            s=Instant.parse(node.get("start_date").asText());
        }
        if(node.get("end_date")!=null) {
            e=Instant.parse(node.get("end_date").asText());
        }
        return new AppointmentGetDTO(s,e);
    }
}
