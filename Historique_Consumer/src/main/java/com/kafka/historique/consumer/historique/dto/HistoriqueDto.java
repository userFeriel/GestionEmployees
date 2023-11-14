package com.kafka.historique.consumer.historique.dto;

import com.kafka.historique.consumer.historique.entity.TYPE_ACTION;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueDto {
    private TYPE_ACTION typeAction;
    private Integer userId;
    private LocalDate doneAt;
    private Object sentData;
}
