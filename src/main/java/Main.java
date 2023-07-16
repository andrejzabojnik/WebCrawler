import java.util.ArrayList;

import Jsoup_crawler.ArgumentParser;
import Jsoup_crawler.Crawler;
import Jsoup_crawler.InputReader;
import Jsoup_crawler.exception.InvalidInputFileException;
import Jsoup_crawler.exception.UnknownParametersException;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import sk.textprocessor.exceptions.InvalidParametersCombinationException;


public class Main {

    public static void main(String[] args) {

        try{
            DetectorFactory.loadProfile("src/main/profiles");
        } catch (LangDetectException e) {
            e.printStackTrace();
        }

        try {
            InputReader inputReader = new InputReader();
            Crawler crawler = new Crawler();
            ArgumentParser ArgumentParser = new ArgumentParser(args);
            if(Jsoup_crawler.ArgumentParser.checkNumberOfParameters()  > 0){
                ArgumentParser.processTextArgument();
            }

            ArrayList<String> urlList = inputReader.readFile(ArgumentParser.getInputFile());

            long depth = ArgumentParser.getDepth();

            for (String url : urlList) {
                crawler.crawl(depth, url, new ArrayList<>());
            }

            System.out.println("Writing to the file was successful");

        } catch (InvalidInputFileException | UnknownParametersException | InvalidParametersCombinationException e) {
            System.out.println(e.getMessage());

        }
    }
}