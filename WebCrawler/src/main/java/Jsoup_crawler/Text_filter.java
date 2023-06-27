package Jsoup_crawler;

public class Text_filter {
    static String text_filter(String text) {
        String s = "";
        boolean write = true;
        boolean first = true;

        for (int i = 0; i < text.length(); i++) {
            char znak = text.charAt(i);

            if (znak == '<') {
                if(!first){
                    write = false;
                }
            } else if(first && znak == ' '){
                write = false;
            }else if (znak == '>') {
                if(first) {
                    s += ": ";
                    first = false;
                }
                write = true;
            } else if (write) {
                s += text.substring(i,i+1);
            }
        }

        return s;
    }
}
