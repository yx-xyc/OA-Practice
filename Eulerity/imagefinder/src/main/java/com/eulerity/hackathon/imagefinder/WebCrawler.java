package com.eulerity.hackathon.imagefinder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A web crawler that searches for images on a website.
 */
public class WebCrawler {
    /**
     * The current DFS search level
     */
    private int dfsDepthLeve = 0;
    /**
     * The number of URLs that failed to be accessed.
     */
    private int failedURLNum = 0;
    /**
     * The number of image sources that failed to be accessed.
     */
    private int failedImgSrcNum = 0;
    /**
     * The string of "login" to stop infinite loop of visiting login page.
     */
    private final String loginString = "login";
    /**
     * The user agent string to use when making HTTP requests.
     */
    private final String userAgent = "Mozilla/5.0%20(Windows%20NT%2010.0;%20Win64;%20x64)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Chrome/58.0.3029.110%20Safari/537.3";
    /**
     * The starting URL for the web crawl.
     */
    private final String startURL;
    /**
     * The domain name of the website being crawled.
     */
    private final String domainName;
    /**
     * The depth level at which to stop the depth-first search.
     */
    public int deepestTraverseLevel = 100;
    /**
     * The sleep time in millis .
     */
    public int sleepTimeInMillis = 100;
    /**
     * The set of visited URLs.
     */
    public HashSet<String> visitedURLs;
    /**
     * The set of image sources found during the crawl.
     */
    public HashSet<String> imgSrcs;
    /**
     * Constructor for WebCrawler class.
     * @param startURL the start URL for web crawling.
     * @throws MalformedURLException if the URL is malformed.
     */
    public WebCrawler(String startURL) throws MalformedURLException {
        this.startURL = startURL;
        URL url = new URL(Utility.encodeURL(startURL));
        this.domainName = url.getProtocol()+"://" +url.getHost();
        this.visitedURLs = new HashSet<>();
        this.imgSrcs = new HashSet<>();
    }
    /**
     * Finds all URLs in the same domain with DFS starting from the start URL.
     */
    public void getAllURLs() {
        System.out.println("Start to collect URLS.");
        findURLsWithDFS(this.startURL);
        System.out.println("All URLs in the same domain collected.");
    }
    /**
     * A helper function to recursively find all URLs in the same domain with DFS starting from the input URL.
     * @param url the input URL to be visited.
     */
    private void findURLsWithDFS(String url) {
        this.dfsDepthLeve++;
        // stop recursion when the level is too deep
        if (this.dfsDepthLeve>this.deepestTraverseLevel) {
            System.out.println("Reach deepest DFS level.");
            return;
        }
        // stop recursion when reach the login page
        if (url.contains(this.loginString)) {
            return;
        }
        // while the url has not been visited before
        if (!visitedURLs.contains(url)) {
            try {
                Connection conn = Jsoup.connect(url).userAgent(this.userAgent);
                Document document = conn.get();
                Elements subUrls = document.select("a[href^=\""+this.domainName+"\"]");
                visitedURLs.add(url);
                for (Element subUrl : subUrls) {
                    String address = subUrl.attr("abs:href");
                    findURLsWithDFS(address);
                    this.dfsDepthLeve--;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                this.failedURLNum++;
            }
        }
        // Delay request to avoid send too many requests in a short period of time
        try {
            Thread.sleep(this.sleepTimeInMillis);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Helper method that scrapes image sources from a given URL and adds them to the instance's imgSrcs HashSet.
     * @param url The URL to scrape image sources from.
     */
    private void getImgs(String url) {
        String postfix = url.substring(url.length() - 4).toLowerCase();
        // if the url itself is an image address, we store it as source and return
        if (postfix.equals(".jpg")||postfix.equals(".png")) {
            this.imgSrcs.add(url);
            return;
        }
        try {
            Connection conn = Jsoup.connect(url).userAgent(this.userAgent);
            Document document = conn.get();
            Elements images = document.select("img");
            for (Element image : images) {
                String imgSrc = image.attr("abs:src");
                addToImgSrcs(imgSrc);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            this.failedImgSrcNum++;
        }
        // Delay request to avoid send too many requests in a short period of time
        try {
            Thread.sleep(this.sleepTimeInMillis);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Collects all image sources from all the collected URLs sequentially.
     */
    public void getAllImgs() {
        System.out.println("Start to collect images sources from all of the collected URLs.");
        for (String url : visitedURLs) {
            getImgs(url);
        }
        System.out.println("All image sources collected.");
    }
    /**
     * Collects all image sources from all the collected URLs with multiple threads.
     */
    public void getAllImgsMultiThread() {
        System.out.println("Start to collect images sources from all of the collected URLs with multi-threads.");
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (String url : visitedURLs) {
            executorService.submit(()-> getImgs(url));
        }
        executorService.shutdown();
        System.out.println("All image sources collected.");
    }
    /**
     * Helper method that adds a given URL String to the instance's imgSrcs set in a thread-safe manner.
     * @param url The URL to add to the HashSet.
     */
    private synchronized void addToImgSrcs(String url){
        this.imgSrcs.add(url);
    }
    /**
     * Reports the total number of visited URLs and scraped image sources to the console.
     */
    public void reportResult() {
        System.out.println("Find "+this.visitedURLs.size()+" web pages and "+this.imgSrcs.size()+" images in total.");
        System.out.println("Fail to scrape "+this.failedURLNum+" URLs and fail to scrape "+this.failedImgSrcNum+" images.");
    }
    /**
     * Main method that initializes a WebCrawler instance with a starting URL, test methods above
     * @param args command-line arguments (not used)
     * @throws IOException if an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        String urlString = "https://cs.nyu.edu/home/undergrad/overview.html";
        WebCrawler wc = new WebCrawler(urlString);
        wc.getAllURLs();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("DFS\n\tExecution time: " + executionTime + " milliseconds.");
        for (String url : wc.visitedURLs) {
            System.out.println(url);
        }

        startTime = System.currentTimeMillis();
        if (wc.visitedURLs.size()>50) {
            wc.getAllImgsMultiThread();
        } else {
            wc.getAllImgs();
        }
        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds.");
        for (String imgSrc : wc.imgSrcs) {
            System.out.println(imgSrc);
        }

        wc.reportResult();
    }
}
