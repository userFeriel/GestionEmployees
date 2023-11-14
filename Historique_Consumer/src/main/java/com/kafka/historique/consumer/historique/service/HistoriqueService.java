package com.kafka.historique.consumer.historique.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.historique.consumer.historique.entity.Historique;
import com.kafka.historique.consumer.historique.repository.HistoriqueRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoriqueService {
    private final HistoriqueRepository historiqueRepository;
    @Autowired
    ObjectMapper objectMapper;
    public HistoriqueService(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    public void processHistoryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        Historique historique =  objectMapper.readValue(consumerRecord.value(), Historique.class);;
        log.info("historique : {} ", historique);
        historiqueRepository.save(historique);
        log.info("new record of history saved successfully");
    }

}
