package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {
    public void export(OutputStream os, List<ClientDTO> clients) throws IOException{
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("listes clients");


        XSSFRow rowhead = sheet.createRow(0);
        rowhead.createCell(0).setCellValue("Nom");
        rowhead.createCell(1).setCellValue("Prenom");


        for (int i = 0; i < clients.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(clients.get(i).getNom());
           row.createCell(1).setCellValue (clients.get(i).getPrenom());
        }

        workbook.write(os);
        workbook.close();
    }
    
    
    public void exportFacturesClient(OutputStream os, List<FactureDTO> facturesClient) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
    	XSSFSheet sheet = workbook.createSheet("client");
    	Double Total = 0.0;
    	Double PrixLigne = 0.0;
    	
    	

    	
    	// En Tête
    	XSSFRow Rowhead = sheet.createRow(0);
    	Rowhead.createCell(0).setCellValue("NOM");
    	Rowhead.createCell(1).setCellValue("PRENOM");

   
    	Integer i=1;
    	for(FactureDTO factures : facturesClient) {
    		XSSFRow headerRow = sheet.createRow(1);
    		
    		 headerRow.createCell(0).setCellValue(factures.getClient().getNom());
    		
    		 headerRow.createCell(1).setCellValue(factures.getClient().getPrenom());
    		
    		XSSFSheet sheet2 = workbook.createSheet("Facture numero " + String.valueOf(factures.getId()));
    		
    		// l' entête de la facture
    		XSSFRow header2 = sheet2.createRow(0);
        	header2.createCell(0).setCellValue("Libellé Article");
        	header2.createCell(1).setCellValue("Prix Unitaire");
        	header2.createCell(2).setCellValue("Quantité");
        	header2.createCell(3).setCellValue("Montant Ligne");
        	
        	
        	Integer j = 1;
        	
        	for (LigneFactureDTO lignesFac : factures.getLigneFactures()) {
        		XSSFRow Row = sheet2.createRow(j);
        		Row.createCell(0);
        		//cellule designation
        		Row.createCell(0).setCellValue(lignesFac.getDesignation());
        		//cellule prix unitaire
        		 Row.createCell(1).setCellValue(lignesFac.getPrixUnitaire() + " €");
        		 //cellule quantité
        		  Row.createCell(2).setCellValue(lignesFac.getQuantite());
        		// calcule du montant du nombre d'artilce
        		PrixLigne = lignesFac.getQuantite() * lignesFac.getPrixUnitaire();
        		Total += PrixLigne;
        		//cellule totale
        		 Row.createCell(3).setCellValue(String.valueOf(PrixLigne)  + " €");
        		
        		j++;
        	}
        	
        	// Ligne Total
        	XSSFRow RowTotal = sheet2.createRow(j);
        	RowTotal.createCell(0).setCellValue("Total");
        	RowTotal.createCell(1).setCellValue("");
        	RowTotal.createCell(2).setCellValue("");
        	RowTotal.createCell(3).setCellValue(String.valueOf(Total) + " €");
    		
    		i++;
    	}
    	
    	workbook.write(os);
    	workbook.close();
	}
}
