package org.example.service;
import java.io.*;
import java.util.*;


public class FileService {
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            for(String line : lines){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea fisierului: " + e.getMessage());
        }
    }
}
