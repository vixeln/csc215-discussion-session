package com.github.hexadecalice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.net.http.HttpTimeoutException;
import java.util.HashSet;
import java.util.UUID;
import java.util.Optional;
import java.util.Arrays;


public class Crawler {
	public static final HashSet<String> NOT_FOUND_PHRASES = new HashSet<>(Arrays.asList(
		"<title> 404",
		"404 |",
		"go home",
        "404 not found",
        "404 - not found",
        "page not found",
        "file not found",
        "the requested url was not found",
        "error 404",
        "404 error",
        "content missing",
        "no content found",
        "this directory is empty",
        "no files found",
        "resource not available",
        "invalid url",
        "oops! that page can’t be found",
        "nothing found at this location",
        "the page you were looking for doesn't exist",
        "sorry, we couldn't find that page",
        "the page cannot be found"
    ));
    public static void crawl(HashSet<String> wordList, String hostName) throws Exception {

        HttpClient myClient = HttpClient.newBuilder()
                                        .version(HttpClient.Version.HTTP_1_1)
                                        .build();

        //Sends request to a directory that doesnt exist, if it returns 200, save the content-length header
        //This will be used against future 200 status codes to ensure they're real
        boolean soft404 = true;
        long spoofLength = -1;


        String randomString = UUID.randomUUID().toString();

        HttpRequest soft404Tester = HttpRequest.newBuilder()
                                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0")
                                    .uri(URI.create("https://www." + hostName + "/" + randomString))
                                    .timeout(Duration.ofSeconds(2))
                                    .method("HEAD", BodyPublishers.noBody())
                                    .build();
        System.out.println(randomString);

        HttpResponse<Void> spoofResponse = myClient.send(soft404Tester, BodyHandlers.discarding());
        int spoofStatus = spoofResponse.statusCode();

        if(spoofStatus == 200) {
            Optional<String> spoofLengthBox = spoofResponse.headers().firstValue("Content-Length");
            if(spoofLengthBox.isPresent()) {
                String spoofLengthStr = spoofLengthBox.get();
                spoofLength = Long.parseLong(spoofLengthStr);
                soft404 = true;
            }else {
                System.out.println("Spoof request didn't receive content length header, 200 status codes may be soft 404's");
            }
        }



        for(String dir : wordList){

            try {
                //Creates the http request, tries to make it stealthy with user agent
                HttpRequest request = HttpRequest.newBuilder()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0")
                    .uri(URI.create("https://www." + hostName + "/" + dir))
                    .timeout(Duration.ofSeconds(2))
                    .method("HEAD", BodyPublishers.noBody())
                    .build();

                 HttpRequest deepRequest = HttpRequest.newBuilder()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0")
                    .uri(URI.create("https://www." + hostName + "/" + dir))
                    .timeout(Duration.ofSeconds(2))
                    .GET()
                    .build();

                //Send the request, and discards whatever body might be in the response
                HttpResponse<Void> response = myClient.send(request, BodyHandlers.discarding());
                int statusCode = response.statusCode();

                //Checks to ensure that the 200 status code is real by comparing content length headers
                if(soft404){
                    Optional<String> lengthBox = response.headers().firstValue("Content-Length");
                    if(lengthBox.isPresent()) {
                        String strLength = lengthBox.get();
                        long contentLength = Long.parseLong(strLength);

                        //Checks if the 200 has the same content-length header as the soft 404
                        //The +/- 100 is because URL length can affect the web page, its definitely an arbitrary value
                        if(contentLength < (spoofLength+300) && contentLength >= (spoofLength-300)) {
                            continue;
                        }
                        else {
							HttpResponse<String> bodyResponse = myClient.send(deepRequest, BodyHandlers.ofString());
							String pageBody = bodyResponse.body().toLowerCase();
							boolean isFake = false;
							for(String word : NOT_FOUND_PHRASES) {
								if(pageBody.contains(word)) {
									isFake = true;
									break;
								}
							}
							if(isFake) {
								continue;
							}

						}
                    }
                }

                if(statusCode != 404) {
                    String stringStatus = Integer.toString(statusCode);
                    System.out.println("Directory " + dir + " returning status code " + stringStatus);
                }

            } catch(HttpTimeoutException e) {
                System.out.println("Host at " + dir + " timed out");
            }
        }
    }
}
