package ua.com.alevel.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvWriterReader {

    private String filename;
    private String testRow;

    public CsvWriterReader(String filename) {
        this.filename = filename;
        Path path = Paths.get(filename);
        try {
            boolean exists = Files.exists(path);
            if (!exists) {
                String fileDirectory = filename.substring(0, filename.lastIndexOf("/"));
                Files.createDirectories(Paths.get(fileDirectory));
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<? extends String[]> readFromCSV() {
        List<String[]> values = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((testRow = br.readLine()) != null) {
                String[] line = testRow.split(",");
                values.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found " + filename);
        } catch (IOException e) {
            System.out.println("ERROR: Could not read " + filename);
        }
        return values;
    }

    public void writeToCSV(List<String[]> testRow) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < testRow.size(); i++) {
                for (int j = 0; j < testRow.get(i).length; j++) {
                    wr.write(testRow.get(i)[j]);
                    if (j != testRow.get(i).length - 1)
                        wr.write(",");
                }
                if (i != testRow.size() - 1)
                    wr.write("\n");
            }
            wr.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}