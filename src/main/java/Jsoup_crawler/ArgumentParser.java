package Jsoup_crawler;

import Jsoup_crawler.exception.UnknownParametersException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import sk.textprocessor.exceptions.InvalidParametersCombinationException;
import sk.textprocessor.exceptions.InvalidTextProcessingTypeException;
import sk.textprocessor.processing.TextProcesses;


public class  ArgumentParser {
    TextProcesses TextProcesses = new TextProcesses();



//    parameters for TextProcesses


    @Parameter(names = "-lemma", description = "Lematize text")
    private static boolean lemmatize = false;

    @Parameter(names = "-analyze", description = "Morphological analyze")
    private static boolean analyze = false;
    @Parameter(names = "-token", description = "Tokenize text")
    private static boolean tokenize = false;

    @Parameter(names = "-extsents", description = "Extract sentences")
    private static boolean extractSentences = false;
    @Parameter(names = "-depth", description = "Depth of crawling")
    private static long depth = 0;

    @Parameter(names = "-a", description = "Links crawler")
    private static boolean links = false;

    @Parameter(names = "-p", description = "Paragraph crawler")
    private static boolean paragraph = false;

    @Parameter(names = "-h", description = "Heading crawler")
    private static boolean heading = false;

    //    file parameters
    @Parameter(names = "-input", description = "Input file")
    private static String inputFile = "";

    @Parameter(names = "-print", description = "Print text")
    private static boolean printText = false;

    @Parameter(names = "-newFile", description = "Create new file")
    private static String newFile = "";

    //  jCommander instance
    public ArgumentParser(String[] args) throws InvalidParametersCombinationException, UnknownParametersException {
        JCommander jCommander = JCommander.newBuilder()
                .addObject(this)
                .build();
        try {
            jCommander.parse(args);
            if(checkNumberOfParameters() >  1) {
                throw new InvalidParametersCombinationException("Error: You cannot specify two text processing parameters at the same time");
            }

        }
        catch (ParameterException e) {
            throw new UnknownParametersException("Error: The specified parameters do not exist");
        }
    }

    public static int  checkNumberOfParameters(){
        boolean[] options = {tokenize, extractSentences,analyze,lemmatize};
        int count = 0;
        for (int i = 0; i < options.length; i++) {
            if (options[i]) {
                count++;
            }
        }
        return count;

    }


    public void processTextArgument()  {
            if (this.isTokenize()) {
                NPL.parameter = "token";
            } else if (this.isExtractSentences()) {
                NPL.parameter = "extsents";
            } else if (this.isAnalyze()) {
                NPL.parameter = "analyze";
            } else if (this.isLemmatize()) {
                NPL.parameter = "lemma";
            }
        }




    //  functions
    public static String getNewFileName() { return newFile;}

    public static boolean isParagraph() {
        return paragraph;
    }

    public static boolean isHeading() {
        return heading;
    }
    public static boolean isLinks() { return links;}

    public static long getDepth() { return depth;}

    public static String getInputFile() {
        return inputFile;
    }
    public static boolean isPrintText() {
        return printText;
    }
    public static boolean isLemmatize() {
        return lemmatize;
    }
    public static boolean isAnalyze() {
        return analyze;
    }
    public static boolean isTokenize() {
        return tokenize;
    }
    public static boolean isExtractSentences() {
        return extractSentences;
    }


}


