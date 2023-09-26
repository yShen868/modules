package com.consumer.consumer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "consumer.config")
@Getter
@Setter
public class ConsumerProperties {


    private String name;


}