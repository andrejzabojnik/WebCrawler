package Jsoup_crawler;

import Jsoup_crawler.exception.InvalidInputFileException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {

    public ArrayList<String> readFile(String fileName) throws InvalidInputFileException {
        ArrayList<String> urls = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                urls.add(line);
            }

            return urls;

        } catch (IOException e) {
            throw new InvalidInputFileException("Error: Invalid file specified");
        }
    }
}







