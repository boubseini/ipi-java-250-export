package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
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
        XSSFSheet sheet=workbook.createSheet("boubacar");
        workbook.write(os);
        workbook.close();
    }
}
