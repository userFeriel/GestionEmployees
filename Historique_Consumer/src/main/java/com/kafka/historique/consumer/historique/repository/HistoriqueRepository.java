package com.kafka.historique.consumer.historique.repository;

import com.kafka.historique.consumer.historique.entity.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
}
