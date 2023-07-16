package Jsoup_crawler;

public class Text_filter {
    static String text_filter(String text) {
        StringBuilder s = new StringBuilder();
        boolean write = true;
        boolean first = true;

        for (int i = 0; i < text.length(); i++) {
            char sign = text.charAt(i);

            if (sign == '<') {
                if(!first){
                    write = false;
                }
            } else if(first && sign == ' '){
                write = false;
            }else if (sign == '>') {
                if(first) {
                    s.append(": ");
                    first = false;
                }
                write = true;
            } else if (write) {
                s.append(text.charAt(i));
            }
        }

        return s.toString();
    }
}
