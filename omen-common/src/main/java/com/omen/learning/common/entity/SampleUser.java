package com.omen.learning.common.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SampleUser<T> {

    private String userName;

    private Integer age;

    private LocalDateTime birthday;

    private T sampleDog;


}