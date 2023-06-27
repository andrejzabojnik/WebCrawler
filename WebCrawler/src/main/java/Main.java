import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Jsoup_crawler.ArgumentParser;
import Jsoup_crawler.Crawler;
import Jsoup_crawler.InputReader;
import Jsoup_crawler.exception.InvalidInputFileException;
import Jsoup_crawler.exception.InvalidOutputFileException;
import Jsoup_crawler.exception.InvalidParametersCombinationException;
import Jsoup_crawler.exception.UnknownParametersException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) {



        try {
            InputReader inputReader = new InputReader();
            Crawler crawler = new Crawler();
            ArgumentParser ArgumentParser = new ArgumentParser(args);


            // VSTUP

            // Zoznam URL adries stránok, ktoré chceme prehľadať
            ArrayList<String> urlList = inputReader.readFile(ArgumentParser.getInputFile());

            // Hĺbka prehľadávania stránok
            long depth = ArgumentParser.getDepth();

            // VSTUP

            for (String url : urlList) {
                crawler.crawl(depth, url, new ArrayList<>());
            }
        } catch (InvalidInputFileException | UnknownParametersException e) {
            System.out.println(e.getMessage());

        }
    }
}