package com.eulerity.hackathon.imagefinder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A utility class containing methods for URL validation and URL encoding.
 */
public class Utility {
    /**
     * Validates whether a given string is a valid URL.
     * @param urlString a string representing a URL
     * @return true if urlString is a valid URL, false otherwise
     */
    public static boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            InputStream is = url.openStream();
            Connection conn = Jsoup.connect(urlString);
            Document document = conn.get();
            return true;
        } catch (IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    /**
     * Encodes a given URL string.
     * @param url a string representing a URL
     * @return an encoded version of the URL string
     */
    public static String encodeURL(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        return url.replace(" ", "%20");
    }

}
