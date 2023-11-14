package com.kafka.historique.consumer.kafkaConsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.historique.consumer.historique.entity.Historique;
import com.kafka.historique.consumer.historique.service.HistoriqueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerService {

    @Autowired
    HistoriqueService historiqueService;

    @KafkaListener(
            topics = {"historique-events"}
            , autoStartup = "${libraryListener.startup:true}"
            , groupId = "history-events-listener-group")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {

        log.info("ConsumerRecord : {} ", consumerRecord);
       historiqueService.processHistoryEvent(consumerRecord);

    }
}
