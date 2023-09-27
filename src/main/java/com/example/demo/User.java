package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @UpdateTimestamp
    LocalDateTime updateAt;

    @CreationTimestamp
    LocalDateTime createdAt;

    @Id
    private int id;

    private String name;

    @Column(columnDefinition = "JSON")
    @Convert(converter = InfoConverter.class)
    private List<Info> info;

    @OneToOne(fetch = FetchType.LAZY)
    private PhoneNumber phoneNumber;

    public static final class InfoConverter implements AttributeConverter<List<Info>, String> {

        @Override
        public String convertToDatabaseColumn(List<Info> info) {
            try {
                return new ObjectMapper().writeValueAsString(info);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Info> convertToEntityAttribute(String s) {
            try {
                return new ObjectMapper().readValue(s, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Getter
    public static final class Info {
        private final String major;

        @ConstructorProperties({"major"})
        public Info(String major) {
            this.major = major;
        }

    }
}
