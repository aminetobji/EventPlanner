/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgfinal.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import pkgfinal.util.APIConsumer;
import pkgfinal.util.ImageDownloader;

public class ServiceMedia {
    private String town;
    private String event;
    protected final String xApiKeyFlag = "AekBZhNDnoia9+R5XLx6wQ==QQygnOZ38iaHijoO";
    protected final String xApiKeyPicture = "ca6f708230a789dc70aa0091c51f540e4f0cec0710b63e6e64b401d87c95f823";

    public ServiceMedia(String town,String event) {
        this.town = town;
        this.event = event;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
    public boolean getFlag(){
        try {
            APIConsumer api = new APIConsumer("https://api.api-ninjas.com/v1/city?name=",town,"GET");
            api.addHeaderProperty("X-Api-Key",xApiKeyFlag);
            JsonObject entry = api.consume().getJsonObject(0);
            String country = entry.getString("country");
            ImageDownloader downloader = new ImageDownloader("https://countryflagsapi.com/png/"+country,town+".png");
            downloader.downlaod();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    public boolean getEventPicture(){
        try {
            APIConsumer api = new APIConsumer("https://serpapi.com/search.json?engine=yandex_images&text=",event,"GET");
            api.addHeaderProperty("api_key",xApiKeyPicture);
            api.addHeaderProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
            JsonObject entry = api.consume().getJsonObject(0);
            System.out.println(entry);
            /*ImageDownloader downloader = new ImageDownloader(entry.getJsonArray("hits").getJsonObject(0).getJsonString("largeImageURL").toString().replace("\"","").replace(" ",""),event+".png");
            downloader.downlaod();
            */
            return true;
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace());
            return false;
        }
    }
    
}
