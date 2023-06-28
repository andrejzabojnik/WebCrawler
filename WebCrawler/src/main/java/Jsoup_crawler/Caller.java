package Jsoup_crawler;

record Caller(long deep, String url) {
    public long deep() {
        return deep;
    }

    public String url() {
        return url;
    }
}

