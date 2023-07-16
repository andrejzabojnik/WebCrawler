package Jsoup_crawler;

import Jsoup_crawler.exception.InvalidOutputFileException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Output {

    static void text_output(Document doc, String url) {
        Elements elements = new Elements();
        String filename = ArgumentParser.getNewFileName();
        String selects = "";
        StringBuilder outputBuilder = new StringBuilder();

        outputBuilder.append("URL: ").append(url).append("\n");
        outputBuilder.append(Language_detector.language_detector(doc)).append("\n");
        outputBuilder.append("Title: ").append(doc.title()).append("\n");

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
            outputBuilder.append(NPL.textProcessing(Text_filter.text_filter(element.toString()))).append("\n");
        }

        outputBuilder.append("\n");
        String output = outputBuilder.toString();

        if (ArgumentParser.isPrintText()) {
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
        Path filePath = Paths.get(fullPath);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            Files.write(filePath, outputData.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new InvalidOutputFileException("Error: file could not be created");
        }
    }
}