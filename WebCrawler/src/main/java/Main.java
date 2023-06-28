import java.util.ArrayList;

import Jsoup_crawler.ArgumentParser;
import Jsoup_crawler.Crawler;
import Jsoup_crawler.InputReader;
import Jsoup_crawler.exception.InvalidInputFileException;
import Jsoup_crawler.exception.UnknownParametersException;


public class Main {

    public static void main(String[] args) {


        try {
            InputReader inputReader = new InputReader();
            Crawler crawler = new Crawler();
            new ArgumentParser(args);

            ArrayList<String> urlList = inputReader.readFile(ArgumentParser.getInputFile());

            long depth = ArgumentParser.getDepth();

            for (String url : urlList) {
                crawler.crawl(depth, url, new ArrayList<>());
            }

        } catch (InvalidInputFileException | UnknownParametersException e) {
            System.out.println(e.getMessage());

        }
    }
}