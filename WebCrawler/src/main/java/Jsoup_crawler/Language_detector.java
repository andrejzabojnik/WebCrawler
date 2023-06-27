package Jsoup_crawler;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.jsoup.nodes.Document;

public class Language_detector {

    static String language_detector(Document doc){

        try {

            String text = doc.text();

            com.cybozu.labs.langdetect.Detector detector = DetectorFactory.create();
            detector.append(text);
            String language = detector.detect();

            return "Jazyk webovej stránky: " + language;

        } catch (LangDetectException e) {
            e.printStackTrace();
        }

        return "Jazyk stranky sa nepodarilo rozpoznať.";
    }

}
