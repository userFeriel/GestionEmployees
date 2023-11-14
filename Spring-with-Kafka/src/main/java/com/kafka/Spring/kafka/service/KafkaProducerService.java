package com.kafka.Spring.kafka.service;

import com.kafka.Spring.kafka.model.Historique;
import com.kafka.Spring.kafka.model.TYPE_ACTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KafkaProducerService {
    @Value("${spring.kafka.template.default-topic}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, Historique> kafkaTemplate;

    public void send(Historique historique) {

        kafkaTemplate.send(kafkaTopic, historique);
    }

    public void sendHistoriqueData(TYPE_ACTION typeAction, LocalDate date, Object data) {
        //TODO get userId from securityHandler (connected user)
        Integer userId = 1;
        Historique historique = new Historique(typeAction, userId, date, data);
        kafkaTemplate.send(kafkaTopic, historique);
    }

}
