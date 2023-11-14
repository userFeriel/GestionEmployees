package com.kafka.Spring.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historique {
    private TYPE_ACTION typeAction;
    private Integer userId;
    private LocalDate doneAt;
    private Object sentData;
}
