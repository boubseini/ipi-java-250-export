package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        document.open();


        double Total = 0.0;

        document.add(new Paragraph(facture.getClient().getNom()+" "+facture.getClient().getPrenom()));
        document.add( new Paragraph("Facture numéro "+facture.getId()));
        for (LigneFactureDTO ls : facture.getLigneFactures()) {
            document.add(new Paragraph(" Désignation :"+ls.getDesignation() + "\n" +
                                         "  Quantité :  " + ls.getQuantite() + "\n"+  
            		                        "  Prix : " + ls.getPrixUnitaire() + "€"));
            Total+= ls.getQuantite()*ls.getPrixUnitaire();
        }
        document.add(new Paragraph("Total : " + Total +"€"));
        document.close();
    }
}