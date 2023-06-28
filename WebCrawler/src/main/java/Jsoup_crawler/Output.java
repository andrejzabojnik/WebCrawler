package Jsoup_crawler;

import Jsoup_crawler.exception.InvalidOutputFileException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Output {

    static void text_output(Document doc, String url) {
        Elements elements = new Elements();
        String filename = ArgumentParser.getNewFileName();
        String selects = "";
        String output = "URL: " + url + "\n";
        String title = doc.title();
        output += Language_detector.language_detector(doc) + "\n";
        output += "Title: " + title + "\n";

        if (ArgumentParser.isParagraph()) {
            selects += "p ";
        }

        if (ArgumentParser.isHeading()) {
            if (!selects.isEmpty()) {
                selects += ", ";
            }
            selects += "h1, h2, h3, h4, h5, h6";
        }

        if (ArgumentParser.isLinks()) {
            if (!selects.isEmpty()) {
                selects += ", ";
            }
            selects += "a";
        }

        elements.addAll(doc.select(selects));


        for (Element element : elements) {
            output += Text_filter.text_filter(element.toString()) + "\n";
        }

        output += "\n";

        if(ArgumentParser.isPrintText()){
            System.out.println(output);
        }


        try {
            if (!filename.isEmpty()) {
                createNewFile(filename, output);
            }
        } catch (InvalidOutputFileException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewFile(String fileName, String outputData) throws InvalidOutputFileException {
        String path = System.getProperty("user.home") + "/Desktop";
        String filename = fileName + ".txt";
        String fullPath = Paths.get(path, filename).toString();

        try {
            if (!Files.exists(Paths.get(fullPath))) {
                Files.createFile(Paths.get(fullPath));
            }

            Files.write(Paths.get(fullPath), outputData.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Zápis do súboru prebehol úspešne.");
        } catch (IOException e) {
            throw new InvalidOutputFileException("Chyba: súbor sa nepodarilo vytvoriť");
        }
    }
}