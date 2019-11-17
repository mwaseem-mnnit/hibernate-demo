package com.example.hibernatedemo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomJsonMessageConverter extends AbstractJackson2HttpMessageConverter {

    @Nullable
    private String jsonPrefix;

    public CustomJsonMessageConverter() {
        this(Jackson2ObjectMapperBuilder.json().build().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true));
    }
    public CustomJsonMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, new MediaType[]{ new MediaType("application", "custom")});
    }

    public void setJsonPrefix(String jsonPrefix) {
        this.jsonPrefix = jsonPrefix;
    }

    public void setPrefixJson(boolean prefixJson) {
        this.jsonPrefix = prefixJson ? ")]}', " : null;
    }

    protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
            if (this.jsonPrefix != null) {
            generator.writeRaw(this.jsonPrefix);
        }
    }
}
