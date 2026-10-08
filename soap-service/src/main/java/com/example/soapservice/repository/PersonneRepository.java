package com.example.soapservice.repository;

import com.example.soapservice.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
