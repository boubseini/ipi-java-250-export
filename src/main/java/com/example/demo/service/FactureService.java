package com.example.demo.service;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientMapper clientMapper;

    public List<FactureDTO> findAllFactures() {
        List<Facture> findAll = factureRepository.findAll();
		return findAll.stream().map(this::toDTO).collect(toList());
    }
    
    public List<FactureDTO> findAllByIdClient(Long id){
    	return factureRepository.findAllByClientId(id).stream().map(this::toDTO).collect(toList());
    }

    
    private FactureDTO toDTO(Facture F) {
        FactureDTO factureDTO = new FactureDTO();
        factureDTO.setId(F.getId());
        factureDTO.setClient(clientMapper.map(F.getClient()));
        factureDTO.setLigneFactures(F.getLigneFactures().stream().map(this::mapLigneFacture).collect(toList()));
        return factureDTO;
    }
    
    
    private LigneFactureDTO mapLigneFacture(LigneFacture LF) {
        LigneFactureDTO ligneFactureDTO = new LigneFactureDTO();
        ligneFactureDTO.setDesignation(LF.getArticle().getLibelle());
        ligneFactureDTO.setQuantite(LF.getQuantite());
        ligneFactureDTO.setPrixUnitaire(LF.getArticle().getPrix());
        return ligneFactureDTO;
    }

    public FactureDTO findById(Long id) {
        return factureRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() ->
                        new IllegalArgumentException("Facture inconnu " + id)
                );
    }
}