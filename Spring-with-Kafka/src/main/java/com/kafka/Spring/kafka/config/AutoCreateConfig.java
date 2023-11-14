package com.kafka.Spring.kafka.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {
    @Value("${spring.kafka.template.default-topic}")
    private String kafkaTopic;
    @Bean
    public NewTopic libraryEvents(){
        return TopicBuilder.name(kafkaTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

}