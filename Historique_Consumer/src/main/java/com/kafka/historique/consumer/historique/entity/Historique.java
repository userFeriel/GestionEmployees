package com.kafka.historique.consumer.historique.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Historique")
public class Historique {
    @Id
    @GeneratedValue
    private Integer historiqueId;
    private TYPE_ACTION typeAction;
    private Integer userId;
    private LocalDate doneAt;
    @Column(columnDefinition = "jsonb")
    @Type(value = JsonNodeBinaryType.class)
    private JsonNode sentData;
}