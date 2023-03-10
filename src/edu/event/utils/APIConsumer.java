/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.event.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 *
 * @author Cardinal
 */
public class APIConsumer {
    private String url;
    private String query;
    private String method;
    private Map<String,String> additionalHeaders = new HashMap<String,String>();

    public APIConsumer(String url, String query, String method) {
        this.url = url;
        this.query = query;
        this.method = method;
    }
    public void addHeaderProperty(String header,String value){
        additionalHeaders.put(header, value);
    }
    
    public JsonArray consume() throws MalformedURLException, IOException{
        URL apiURL = new URL(url+query);
        HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
        connection.setRequestMethod(method);
        additionalHeaders.forEach((key, value) -> {
            connection.setRequestProperty(key, value);
        });
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder(); 
       
        while ((inputLine = inputReader.readLine()) != null) {
            response.append(inputLine);
        }
        
        inputReader.close();
        String jsonString = response.toString();
        if (jsonString.charAt(0) != '['){
            jsonString = "[" + jsonString + "]";
        }
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonArray jsonArray = jsonReader.readArray();
        jsonReader.close();
        return jsonArray;
    }
}
