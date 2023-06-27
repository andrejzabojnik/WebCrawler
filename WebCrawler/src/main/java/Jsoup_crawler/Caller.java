package Jsoup_crawler;

class Caller {
    private long deep;
    private String url;

    public Caller(long deep, String url) {
        this.deep = deep;
        this.url = url;
    }

    public long deep(){
        return this.deep;
    }

    public String url(){
        return this.url;
    }


}
