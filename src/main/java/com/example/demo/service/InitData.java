package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() {
        Client client1 = newClient("Seini", "Boubacar", "70 rue jean claude vivant 69100",25);
        em.persist(client1);

        Client client2 = newClient("Rinaldi", "Claire","45 rue felix faure 69006", 35);
        em.persist(client2);

        Client client3 = newClient("Bourgeois", "Amandine","46 rue pierre semard 69600",29);
        em.persist(client3);

        Article article1 = newArticle("Carte mère ASROCK 2345", 79.90,"blabal");
        em.persist(article1);

        Article article2 = newArticle("Clé USB", 9.90,"bidull");
        em.persist(article2);

        Article article3 = newArticle("Carte SD", 21.90,"machin");
        em.persist(article3);


        {
            Facture facture = newFacture(client1);
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 1));
        }
        {
            Facture facture = newFacture(client2);
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 1));
            em.persist(newLigneFacture(article2, facture, 5));
        }

        {
            Facture facture = newFacture(client3);
            em.persist(facture);
            em.persist(newLigneFacture(article1, facture, 3));
            em.persist(newLigneFacture(article2, facture, 10));
            em.persist(newLigneFacture(article3, facture, 5));
        }
    }

    private Client newClient(String nom, String prenom,String adresse, Integer age) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setAge(age);
        return client;
    }

    private Article newArticle(String libelle, double prix, String marque) {
        Article article = new Article();
        article.setLibelle(libelle);
        article.setMarque(marque);
        article.setPrix(prix);
        return article;
    }

    private Facture newFacture(Client client) {
        Facture facture = new Facture();
        facture.setClient(client);
        return facture;
    }

    private LigneFacture newLigneFacture(Article article, Facture facture, int quantite) {
        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(quantite);
        return ligneFacture1;
    }
}