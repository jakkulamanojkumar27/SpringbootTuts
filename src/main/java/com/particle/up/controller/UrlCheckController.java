package com.particle.up.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "site is UP";
    private final String SITE_IS_DOWN = "site is DOWN";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCategory = conn.getResponseCode() / 100;
            // System.out.println(responseCategory);
            if (responseCategory != 2 && responseCategory != 3) {
                returnMessage = SITE_IS_DOWN;
            } else {
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            System.out.println(e);
            returnMessage = SITE_IS_DOWN;
        }
        // System.out.println(returnMessage);
        return returnMessage;
    }
}
