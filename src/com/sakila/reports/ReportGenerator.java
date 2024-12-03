package com.sakila.reports;

import com.fasterxml.jackson.databind.ObjectMapper; 
import java.io.File; 
import java.io.IOException; 
import java.util.List;

import com.sakila.data.Film; 

public class ReportGenerator {
    public void generateReport(List<Film> films) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("films.json"), films);
            System.out.println("Reporte generado: films.json");
        } catch (IOException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
        }
    }
    
    public void exportFilmsToJson(List<Film> films) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("films.json"), films);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
