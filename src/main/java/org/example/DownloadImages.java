package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

/*
      The code loads the HTML page from the specified URL,
     extracts all images from HTML and saves
          them in the "data" folder with names corresponding to their serial number.
*/

public class DownloadImages {
    public static void main(String[] args) throws Exception {

        // URL of the page to be parsed
        String url = "https://www.wikipedia.org/";
        // Load HTML pages using Jsoup
        Document doc = Jsoup.connect(url).get();
        // Extract all images from HTML
        Elements images = doc.select("img");
        // Create a HashSet to store links to images
        HashSet<String> links = new HashSet<>();

        // Add absolute links to images in the HashSet
        for (Element image : images) {
            links.add(image.attr("abs:src"));
        }
        int number = 1;
        // Walk through each image link
        for (String link : links) {

            // Extract the file extension from the link
            String extension = link.replaceAll("^.+\\.", "").replace("?.+$", "");
            // Form the path to the file to save
            String filePath = "data/" + number++ + "." + extension;

            try {
                // Download the image
                download(link, filePath);
            } catch (Exception ex) {
                System.out.println("Could not download " + link);
            }
        }
    }

    // Method for downloading a file from a link
    private static void download(String link, String filePath) throws Exception {

        // Open a connection to the URL
        URLConnection connection = new URL(link).openConnection();
        // Get the input stream for reading data
        InputStream inputStream = connection.getInputStream();
        // Create an output stream to write data to a file
        FileOutputStream outputStream = new FileOutputStream(filePath);
        int b;
        // Read data from the input stream and write to the output stream
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }

        // Flush the buffer and close the streams
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}