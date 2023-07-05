package Jsoup_crawler;

import sk.textprocessor.processing.TextProcesses;



import java.util.Arrays;

public class NPL {
    static String textProcessing(String text){
        TextProcesses textProcesses = new TextProcesses();
        String[] output = textProcesses.lemmatize(text);

        return Arrays.toString(output);
    }
}
