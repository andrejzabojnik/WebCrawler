package Jsoup_crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Crawler {

    //method for scanning subpages
    public void crawl(long deep, String url, ArrayList<String> visited) {
        Stack<Caller> stack = new Stack<>();
        deep *= -1;
        stack.push(new Caller(deep, url));

        try{
            DetectorFactory.loadProfile("src/main/profiles");
        } catch (LangDetectException e) {
            e.printStackTrace();
        }

        while (!stack.isEmpty()) {
            Caller frame = stack.pop();
            deep = frame.deep();
            url = frame.url();

            Document doc = request(url, visited);

            if (doc != null && deep <= -1) {
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if (!visited.contains(next_link)) {
                        stack.push(new Caller(deep++, next_link));
                    }
                }
            }
        }
    }

    //method to check if the page can be opened
    private Document request(String url, ArrayList<String> v){
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if(con.response().statusMessage().equals("OK")){

                //page content listing
                Output.text_output(doc, url);

                //add to checked pages

                v.add(url);

                return doc;
            }
            return null;
        }
        catch (IOException | IllegalArgumentException e){
            return null;
        }

    }


}
