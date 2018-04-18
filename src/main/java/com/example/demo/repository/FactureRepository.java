package com.example.demo.repository;

import com.example.demo.entity.Facture;
import com.sun.security.ntlm.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	public List<Facture> findAllByClientId(Long id);
}