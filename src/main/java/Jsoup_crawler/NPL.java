package Jsoup_crawler;

import sk.textprocessor.processing.TextProcesses;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class NPL {
    static String parameter = "";

    static String[] textProcessing(String text) {
        TextProcesses textProcesses = new TextProcesses();
        if (parameter.equals("token")) {
            return textProcesses.tokenize(text);
        } else if (parameter.equals("extsents")) {
            return textProcesses.extractSentences(text);
        } else if (parameter.equals("lemma")) {
            return textProcesses.lemmatize(text);
        } else if (parameter.equals("analyze")) {
            LinkedHashMap<String, String> output = textProcesses.analyze(text);

            String[] array = new String[output.size()];
            int index = 0;
            for (Map.Entry<String, String> entry : output.entrySet()) {
                array[index++] = entry.getKey() + "=" + entry.getValue();
            }
            return array;
        } else {
            return new String[] { text };
        }
    }
}

